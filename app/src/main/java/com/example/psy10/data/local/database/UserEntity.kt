package com.example.psy10.data.local.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val lastUpdated: Long
)

// Klasa reprezentująca użytkownika z jego psami
data class UserWithDogs(
    @Embedded
    val user: UserEntity,
    @ColumnInfo(name = "dog_names")
    val dogNamesString: String? // Przechowujemy jako pojedynczy string
)

@Dao
interface UserEntityDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Transaction
    @Query("""
        SELECT u.*, 
        (SELECT GROUP_CONCAT(d.name)
         FROM dogs d 
         WHERE d.ownerName = u.name) as dog_names
        FROM users u
    """)
    fun getUsersWithDogs(): Flow<List<UserWithDogs>>

    @Query("DELETE FROM users")
    suspend fun clearUsers()
}