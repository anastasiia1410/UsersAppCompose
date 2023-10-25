package com.example.usersappcompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.usersappcompose.data.db.entity.CategoryConverter
import com.example.usersappcompose.data.db.entity.ContactDatabase
import com.example.usersappcompose.data.db.entity.UserDatabase

@Database(entities = [UserDatabase::class, ContactDatabase::class], version = 1)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun contactDao(): ContactDao
}