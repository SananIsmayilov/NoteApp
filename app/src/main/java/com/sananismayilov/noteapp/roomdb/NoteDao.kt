package com.sananismayilov.noteapp.roomdb


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sananismayilov.noteapp.model.Note
import kotlin.coroutines.Continuation

@Dao
interface NoteDao {


    @Query("SELECT * FROM note")
    suspend fun getallnote() : List<Note>
    @Insert
    fun insertnote(note: Note)

    @Delete
    fun deleteNote(note: Note)





}