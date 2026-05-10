package com.sementa.poc_local_database

import com.sementa.poc_local_database.database.DefaultUserLocalDataSource
import com.sementa.poc_local_database.database.UserLocalDataSource
import com.sementa.poc_local_database.database.getRoomDatabase
import com.sementa.poc_local_database.database.getUserDao
import com.sementa.poc_local_database.repositories.DefaultUserRepository
import com.sementa.poc_local_database.repositories.UserRepository
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            provideDataSourceModule,
            provideRepositoryModule,
            provideDatabaseModule
        )
    }

val provideDataSourceModule = module {
    singleOf(::DefaultUserLocalDataSource).bind(UserLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::DefaultUserRepository).bind(UserRepository::class)
}

val provideDatabaseModule = module {
    single { getRoomDatabase(get()) }
    single { getUserDao(get()) }
}