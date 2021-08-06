package com.example.todolistapp.data

import android.content.Context
import androidx.room.*
import com.example.todolistapp.data.convertor.Convertor
import com.example.todolistapp.data.models.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = true)
@TypeConverters(Convertor::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    // companion object is the same as public static final class in java
    companion object{

        // Volatile means writes to this field are immediately made visible to other threads.
        @Volatile
        private var INSTANCE : ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            /*
            When a thread calls synchronized, it acquires the lock of that synchronized block.
            Other threads don't have permission to call that same synchronized block as long
            as previous thread which had acquired the lock does not release the lock.
             */
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }

    }

}