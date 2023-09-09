package com.example.contactapp.ui.viewmodel

import com.example.contactapp.data.Contact
import com.example.contactapp.event.SortType

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)