package com.sananismayilov.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note")
data class Note  (
    @ColumnInfo(name = "tittle")
    var tittle: String?,
    @ColumnInfo(name = "note")
    var note: String?


) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}