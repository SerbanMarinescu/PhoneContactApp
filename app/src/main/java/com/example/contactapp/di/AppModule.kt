package com.example.contactapp.di

import android.content.Context
import androidx.room.Room
import com.example.contactapp.data.database.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = ContactDatabase::class.java,
        name = "Contact.db"
    ).build()

    @Singleton
    @Provides
    fun provideContactDao(db: ContactDatabase) = db.contactDao
}