package com.sementa.poc_local_database

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class MainApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

}

val appModule = module {
    viewModelOf(::UserListViewModel)
    viewModelOf(::UserFormViewModel)
}