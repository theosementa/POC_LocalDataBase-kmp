package com.sementa.poc_local_database

import androidx.room.RoomDatabase
import com.sementa.poc_local_database.database.AppDatabase
import com.sementa.poc_local_database.database.getDatabaseBuilder
import com.sementa.poc_local_database.repositories.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
}

class UserRepositoryHelper: KoinComponent {
    private val userRepository: UserRepository by inject()
    fun getUserRepository(): UserRepository = userRepository
}