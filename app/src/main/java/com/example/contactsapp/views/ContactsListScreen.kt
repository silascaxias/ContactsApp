package com.example.contactsapp.views

import android.annotation.SuppressLint
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.contactsapp.R
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.theme.GreenJC
import com.example.contactsapp.viewmodel.ContactViewModel

/**
 * Utils
 * ContactsListScreen
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsListScreen(viewModel: ContactViewModel, navController: NavController) {
    val context = LocalContext.current.applicationContext

    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.height(70.dp),
                title = {
                    Box (
                       modifier = Modifier
                           .fillMaxHeight()
                           .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(text = "Contacts", fontSize = 18.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Contacts", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(painter = painterResource(R.drawable.ic_contact), null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenJC,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = GreenJC,
                onClick = {
                    navController.navigate("addContact")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { paddingValues ->

        val contacts by viewModel.allContacts.observeAsState(initial = emptyList())
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(contacts) { contact ->
                ContactItem(contact = contact) {
                    navController.navigate("contactDetails/${contact.id}")
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
      Row (
          modifier = Modifier.fillMaxWidth()
              .clickable (onClick = onClick)
              .padding(8.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {
          Image(
              painter = rememberAsyncImagePainter(contact.image),
              contentDescription = contact.name,
              modifier = Modifier
                  .size(50.dp)
                  .clip(CircleShape),
              contentScale = ContentScale.Crop
          )
          Spacer(modifier = Modifier.width(16.dp))
          Text(contact.name)
      }
    }
}