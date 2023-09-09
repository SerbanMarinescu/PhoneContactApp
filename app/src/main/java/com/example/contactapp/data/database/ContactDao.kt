package com.example.contactapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM Contact ORDER BY firstName ASC")
    fun getContactsByFirstName(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact ORDER BY lastName ASC")
    fun getContactsByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact ORDER BY phoneNumber ASC")
    fun getContactsByPhoneNumber(): Flow<List<Contact>>
}