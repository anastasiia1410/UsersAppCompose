package com.example.usersappcompose.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.usersappcompose.data.db.entity.UserDatabase

@Dao
interface UserDao {

    @Insert
    suspend fun insertCurrentUser(user: UserDatabase)

    @Insert
    suspend fun insert(users: List<UserDatabase>)

    @Query("SELECT * FROM User WHERE uuid= :uuid")
    suspend fun getCurrentUser(uuid: String = "1"): UserDatabase?

    @Query("SELECT * FROM User WHERE uuid != :uuid")
    suspend fun getUsers(uuid: String = "1"): List<UserDatabase>

    @Query("SELECT * FROM User WHERE uuid= :uuid")
    suspend fun getUserById(uuid: String): UserDatabase

    @Update
    fun updateUser(user: UserDatabase)
}