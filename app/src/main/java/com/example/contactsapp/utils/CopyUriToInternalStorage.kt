package com.example.contactsapp.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

/**
 * Utils
 * ContactsApp
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

class Utils {
    companion object  {
        fun copyUriToInternalStorage(context: Context, uri: Uri, fileName: String): String? {
            val file = File(context.filesDir, fileName)
            return try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    FileOutputStream(file).use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                file.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
