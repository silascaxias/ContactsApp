package com.example.contactsapp.repository

import com.example.contactsapp.model.Contact
import com.example.contactsapp.database.ContactDAO
import com.example.contactsapp.database.ContactDatabase
import kotlinx.coroutines.flow.Flow

/**
 * ContactRepository
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

class ContactRepository(private val db: ContactDatabase) {
    val allContacts: Flow<List<Contact>> = db.contactDAO().getAllContacts()

    suspend fun insert(contact: Contact) = db.contactDAO().insert(contact)

    suspend fun update(contact: Contact) = db.contactDAO().update(contact)

    suspend fun delete(contact: Contact) = db.contactDAO().delete(contact)
}