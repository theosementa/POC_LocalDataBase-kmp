package com.sementa.poc_local_database

import androidx.room.RoomDatabase
import com.sementa.poc_local_database.database.AppDatabase
import com.sementa.poc_local_database.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder(get())
    }
}