package com.sementa.poc_local_database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sementa.poc_local_database.models.UserDomain
import com.sementa.poc_local_database.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDomain>>(emptyList())
    val users: StateFlow<List<UserDomain>> = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _users.value = repository.fetchAllUsers()
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            repository.deleteUser(userId)
            fetchUsers()
        }
    }
}
