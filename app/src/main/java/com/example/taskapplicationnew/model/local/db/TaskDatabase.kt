package com.example.taskapplicationnew.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapplicationnew.model.data.Task

@Database(entities = [Task::class], version = 1 )
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}

