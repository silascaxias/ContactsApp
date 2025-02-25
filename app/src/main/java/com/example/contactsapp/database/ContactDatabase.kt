package com.example.contactsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsapp.model.Contact

/**
 * ContactDatabase
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDAO(): ContactDAO
}