package com.example.contactsapp.views

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.R
import com.example.contactsapp.ui.theme.GreenJC
import com.example.contactsapp.utils.Utils

/**
 * Utils
 * AddContactScreen
 *
 ** Created by Silas S. Caxias on 2/21/2025.
 *
 **/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(viewModel: ContactViewModel, navController: NavController) {
    val context = LocalContext.current.applicationContext

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var name by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.height(70.dp),
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(text = "Add Contact", fontSize = 18.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Add Contact", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(painter = painterResource(R.drawable.ic_add_contact), null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenJC,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageUri?.let { uri: Uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop)
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    launcher.launch("image/*")
                },
                colors = ButtonDefaults.buttonColors(GreenJC)) {
                Text("Choose Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name, onValueChange = { name = it},
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = phoneNumber, onValueChange = { phoneNumber = it},
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            TextField(
                value = email, onValueChange = { email = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    imageUri?.let { uri ->
                        val internalPath = Utils.copyUriToInternalStorage(context, uri, "$name.jpg")
                        internalPath?.let { path ->
                            viewModel.addContact(
                                name = name,
                                image = path,
                                phoneNumber = phoneNumber,
                                email = email
                            )
                            navController.navigate("contactsList") {
                                popUpTo(0)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(GreenJC)
            ) {
                Text("Add Contact")
            }
        }
    }
}