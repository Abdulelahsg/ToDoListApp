package com.example.todolistapp.data.convertor

import androidx.room.TypeConverter
import com.example.todolistapp.data.models.Priority

/*
    Type convertor, the official documentation of Room says that Room provides the functionality for converting between primitive types and boxed types, but
    doesn't allow for object references between entities. That's means Room only allow the types like String and Int ... etc.

    In our DB, we have specify priority attribute, it is possible to use type convertor to convert the object to String when writing in DB and convert the String
    to object when reading from DB.
 */


class Convertor {

    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}