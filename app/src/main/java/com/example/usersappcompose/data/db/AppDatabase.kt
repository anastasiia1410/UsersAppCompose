package com.example.usersappcompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usersappcompose.data.db.entity.UserDatabase

@Database(entities = [UserDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}