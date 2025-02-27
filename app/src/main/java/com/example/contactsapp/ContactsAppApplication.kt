package com.example.contactsapp

import android.app.Application
import com.example.contactsapp.di.databaseModule
import com.example.contactsapp.di.repositoryModule
import com.example.contactsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * ContactsAppApplication
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/27/2025.
 *
 **/

class ContactsAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ContactsAppApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }
}