package com.example.taskapplicationnew.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val body: String,
    @ColumnInfo
    var isCompleted: Boolean = false
)
