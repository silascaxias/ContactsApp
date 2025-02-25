package com.example.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repository.ContactRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ContactViewModel
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

class ContactViewModel(private val repository: ContactRepository): ViewModel() {

	val allContacts: LiveData<List<Contact>> = repository.allContacts.asLiveData()

	fun addContact(name: String, image: String, phoneNumber: String, email: String) {
		viewModelScope.launch {
			val contact = Contact(0, image = image, name = name, phoneNumber = phoneNumber, email = email)
			repository.insert(contact)
		}
	}

	fun updateContact(contact: Contact) {
		viewModelScope.launch {
			repository.update(contact)
		}
	}

	fun deleteContact(contact: Contact) {
		viewModelScope.launch {
			repository.delete(contact)
		}
	}
}
