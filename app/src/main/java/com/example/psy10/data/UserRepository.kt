package com.example.psy10.data

import com.example.psy10.data.local.database.UserEntity
import com.example.psy10.data.local.database.UserEntityDao
import com.example.psy10.data.remote.UserApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import android.util.Log

interface UserRepository {
    val usersWithDogs: Flow<List<UserWithDogs>>
    suspend fun refreshUsers()
}

data class UserWithDogs(
    val id: Int,
    val name: String,
    val dogNames: List<String>
)

class DefaultUserRepository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserEntityDao,
) : UserRepository {

    override val usersWithDogs: Flow<List<UserWithDogs>> =
        userDao.getUsersWithDogs().map { users ->
            users.map { userWithDogs ->
                UserWithDogs(
                    id = userWithDogs.user.id,
                    name = userWithDogs.user.name,
                    dogNames = userWithDogs.dogNamesString?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                )
            }
        }

    override suspend fun refreshUsers() {
        try {
            val remoteUsers = userApi.getUsers()
            userDao.clearUsers()
            userDao.insertUsers(remoteUsers.map {
                UserEntity(
                    id = it.id,
                    name = it.name,
                    lastUpdated = System.currentTimeMillis()
                )
            })
        } catch (e: Exception) {
            Log.e("UserRepository", "Error refreshing users", e)
        }
    }
}