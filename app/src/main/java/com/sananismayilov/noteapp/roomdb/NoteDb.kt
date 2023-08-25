package com.sananismayilov.noteapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sananismayilov.noteapp.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao() : NoteDao

companion object {
    @Volatile
    private var instance: NoteDb? = null
    private var lock = Any()

    operator fun invoke (context: Context) = instance ?: synchronized(lock){
        instance?: makeDatabase(context).also {
            instance = it
        }
    }


    private fun makeDatabase(context : Context) = Room.databaseBuilder(
        context.applicationContext,NoteDb::class.java,"Notedb"
    ).build()
}
}