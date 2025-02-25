package com.example.contactsapp.repository

import com.example.contactsapp.model.Contact
import com.example.contactsapp.database.ContactDAO
import kotlinx.coroutines.flow.Flow

/**
 * ContactRepository
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

class ContactRepository(private val contactDAO: ContactDAO) {
    val allContacts: Flow<List<Contact>> = contactDAO.getAllContacts()

    suspend fun insert(contact: Contact) = contactDAO.insert(contact)

    suspend fun update(contact: Contact) = contactDAO.update(contact)

    suspend fun delete(contact: Contact) = contactDAO.delete(contact)
}