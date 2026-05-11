package com.sementa.poc_local_database

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.compose.viewmodel.koinViewModel

sealed class Screen {
    data object UserList : Screen()
    data object UserForm : Screen()
}

@Composable
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.UserList) }
        val listViewModel: UserListViewModel = koinViewModel()

        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                if (targetState is Screen.UserForm) {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                } else {
                    slideInVertically { -it } togetherWith slideOutVertically { it }
                }
            },
            label = "screen"
        ) { screen ->
            when (screen) {
                is Screen.UserList -> UserListScreen(
                    viewModel = listViewModel,
                    onAddUser = { currentScreen = Screen.UserForm }
                )
                is Screen.UserForm -> UserFormScreen(
                    onDismiss = {
                        listViewModel.fetchUsers()
                        currentScreen = Screen.UserList
                    }
                )
            }
        }
    }
}