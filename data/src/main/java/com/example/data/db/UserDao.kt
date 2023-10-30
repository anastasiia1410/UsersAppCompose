package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.db.entity.UserDatabase

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserDatabase)

    @Query("SELECT * FROM User")
    suspend fun getUser(): UserDatabase?

    @Update
    fun updateUser(user: UserDatabase)
}