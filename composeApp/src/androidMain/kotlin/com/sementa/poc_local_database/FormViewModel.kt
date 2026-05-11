package com.sementa.poc_local_database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sementa.poc_local_database.repositories.UserRepository
import kotlinx.coroutines.launch

class UserFormViewModel(private val repository: UserRepository) : ViewModel() {

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var age by mutableIntStateOf(0)

    val isValid get() = firstName.isNotBlank() && lastName.isNotBlank()

    fun create(onDone: () -> Unit) {
        viewModelScope.launch {
            repository.insertUser(firstName, lastName, age)
            onDone()
        }
    }
}
