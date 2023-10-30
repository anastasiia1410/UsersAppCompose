package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.domain.entity.Category
import com.example.domain.entity.Contact

@Entity(tableName = "Contact")
data class ContactDatabase(
    @PrimaryKey(false)
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val picture: String,
    @TypeConverters(CategoryConverter::class)
    val category: Category,
)

fun Contact.toContactDatabase(): ContactDatabase {
    return ContactDatabase(
        firstName = firstName,
        lastName = lastName,
        email = email,
        uuid = uuid,
        picture = picture,
        category = category
    )
}

fun ContactDatabase.toContact(): Contact {
    return Contact(
        firstName = firstName,
        lastName = lastName,
        email = email,
        uuid = uuid,
        picture = picture,
        category = category
    )
}

class CategoryConverter {

    @TypeConverter
    fun fromString(value: String): Category {
        return Category.valueOf(value)
    }

    @TypeConverter
    fun toString(category: Category): String {
        return category.name
    }
}

