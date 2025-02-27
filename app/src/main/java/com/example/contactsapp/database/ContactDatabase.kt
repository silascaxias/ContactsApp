package com.example.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var instance: ContactDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance ?:
            createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ContactDatabase::class.java,
                "contact_database"
            ).build()
    }
}