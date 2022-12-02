package com.example.taskapplicationnew.model.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskapplicationnew.model.data.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM notes")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM notes WHERE id in (:id)")
    fun getTaskById(id: Int) : Task

    @Update
    fun updateTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}

