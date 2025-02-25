package com.example.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.contactsapp.database.ContactDatabase
import com.example.contactsapp.repository.ContactRepository
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.ContactViewModelFactory
import com.example.contactsapp.views.AddContactScreen
import com.example.contactsapp.views.ContactDetailsScreen
import com.example.contactsapp.views.ContactsListScreen
import com.example.contactsapp.views.EditContactScreen

/**
 * Utils
 * MainActivity
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contact_database").build()

        val repository = ContactRepository(database.contactDAO())

        val viewModel: ContactViewModel by viewModels { ContactViewModelFactory(repository) }

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "contactsList") {
                composable("contactsList") { ContactsListScreen(viewModel, navController) }
                composable("addContact") { AddContactScreen(viewModel, navController) }
                composable("contactDetails/{contactId}") { navBackStackEntry ->
                    val contactId = navBackStackEntry.arguments?.getString("contactId")?.toInt()
                    val contact = contactId?.let {
                        viewModel.allContacts.observeAsState(initial = emptyList()).value.find { it.id == contactId }
                    }
                    contact?.let { ContactDetailsScreen(it, viewModel, navController) }
                }
                composable("editContact/{contactId}") { navBackStackEntry ->
                    val contactId = navBackStackEntry.arguments?.getString("contactId")?.toInt()
                    val contact = contactId?.let {
                        viewModel.allContacts.observeAsState(initial = emptyList()).value.find { it.id == contactId }
                    }
                    contact?.let { EditContactScreen(it, viewModel, navController) }
                }
            }
        }
    }
}