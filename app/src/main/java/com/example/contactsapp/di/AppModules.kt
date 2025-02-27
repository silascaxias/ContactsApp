package com.example.contactsapp.di

import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.contactsapp.database.ContactDatabase
import com.example.contactsapp.repository.ContactRepository
import com.example.contactsapp.viewmodel.ContactViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * AppModules
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/27/2025.
 *
 **/

val databaseModule = module {
    single {
        ContactDatabase(androidContext())
    }
}

val repositoryModule = module {
    singleOf(::ContactRepository)
}

val viewModelModule = module {
    viewModelOf(::ContactViewModel)
}
