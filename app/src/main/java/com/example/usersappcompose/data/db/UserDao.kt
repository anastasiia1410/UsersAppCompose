package com.example.usersappcompose.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.usersappcompose.data.db.entity.UserDatabase

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserDatabase)

    @Query("SELECT * FROM User WHERE uuid != :uuid")
    suspend fun getUsers(uuid: String): List<UserDatabase>

    @Query("SELECT * FROM User WHERE uuid= :uuid")
    suspend fun getUserById(uuid: String): UserDatabase?

    @Query("SELECT * FROM User WHERE category= :category")
    suspend fun getUsersByCategory(category: String): List<UserDatabase>

    @Update
    fun updateUser(user: UserDatabase)

    @Delete
    fun deleteUser(user: UserDatabase)
}