package com.example.psy10.ui.theme.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psy10.data.UserRepository
import com.example.psy10.data.UserWithDogs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val users: StateFlow<List<UserWithDogs>> = userRepository.usersWithDogs.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch {
            userRepository.refreshUsers()
        }
    }
}