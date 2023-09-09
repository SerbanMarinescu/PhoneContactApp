package com.example.contactapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp.data.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val contactDao: ContactDao
}