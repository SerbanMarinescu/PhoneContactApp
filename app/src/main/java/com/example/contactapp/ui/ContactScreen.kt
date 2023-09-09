package com.example.contactapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactapp.event.ContactEvent
import com.example.contactapp.event.SortType
import com.example.contactapp.ui.viewmodel.ContactState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                onEvent(ContactEvent.ShowDialog)
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

            if(state.isAddingContact){
                AddAlertDialog(
                    state = state,
                    onEvent = onEvent
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 5.dp)
            ){
                item{
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Text(
                            text = "Order Contacts:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(5.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                            ){
                            SortType.values().forEach { sortType ->
                                SortTypeButton(
                                    selected = state.sortType == sortType,
                                    text = sortType.name,
                                    onItemCLick = {
                                        onEvent(ContactEvent.SortContacts(sortType))
                                    }
                                )
                            }
                        }

                    }
                }

                items(state.contacts){ contact ->
                    ContactListItem(
                        contactName = "${contact.firstName} ${contact.lastName}",
                        phoneNumber = contact.phoneNumber,
                        onItemCLick = {
                            onEvent(ContactEvent.DeleteContact(contact))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlertDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        title = {
                Text(
                    text = "Add Contact",
                    fontSize = 10.sp
                )
        },
        text = {
               Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                   TextField(
                       value = state.firstName,
                       onValueChange = {
                           onEvent(ContactEvent.SetFirstName(it))
                       },
                       label = {
                           Text(text = "First Name")
                       }
                   )
                   TextField(
                       value = state.lastName,
                       onValueChange = {
                           onEvent(ContactEvent.SetLastName(it))
                       },
                       label = {
                           Text(text = "Last Name")
                       }
                   )
                   TextField(
                       value = state.phoneNumber,
                       onValueChange = {
                           onEvent(ContactEvent.SetPhoneNumber(it))
                       },
                       label = {
                           Text(text = "Phone Number")
                       },
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                   )

               }
        },
        confirmButton = {
            Button(onClick = {
                onEvent(ContactEvent.SaveContact)
            }) {
                Text(text = "Save Contact")
            }
        }
    )
}

@Composable
fun SortTypeButton(
    selected: Boolean,
    text: String,
    onItemCLick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemCLick()
            }
        ) {
        RadioButton(
            selected = selected,
            onClick = {
                onItemCLick()
            }
        )
        Text(text = text)
    }
}

@Composable
fun ContactListItem(
    contactName: String,
    phoneNumber: String,
    onItemCLick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = contactName,
                fontSize = 25.sp
            )
            Text(
                text = phoneNumber,
                fontSize = 20.sp
            )
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Contact",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    onItemCLick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    //ContactScreen(state = ContactState(), onEvent = {})
    //ContactListItem(contactName = "Serban Marinescu", phoneNumber = "0712425269296")
}