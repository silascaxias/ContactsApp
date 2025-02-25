package com.example.contactsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Contact
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

@Entity("contacts")
data class Contact(
    @PrimaryKey(true)
    val id: Int,
    val image: String,
    val name: String,
    val phoneNumber: String,
    val email: String
)
