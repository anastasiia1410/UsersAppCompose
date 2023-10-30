package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.entity.ContactDatabase
import com.example.domain.entity.Category

@Dao
interface ContactDao {

    @Insert
    suspend fun insert(user: ContactDatabase)

    @Query("SELECT * FROM Contact")
    suspend fun getUsers(): List<ContactDatabase>

    @Query("SELECT * FROM Contact WHERE uuid= :uuid")
    suspend fun getUserById(uuid: String): ContactDatabase?

    @Query("SELECT * FROM Contact WHERE category= :category")
    suspend fun getUsersByData(category: Category): List<ContactDatabase>

    @Delete
    fun deleteUser(user: ContactDatabase)
}