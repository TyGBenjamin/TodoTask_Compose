package com.example.taskapplicationnew.repository

import com.example.taskapplicationnew.model.data.Task

/**
 * Repository abstract interface.
 *
 * @constructor Create empty Repository
 */
interface Repository {
    /**
     * Get contacts from database foTask
     * @return
     */
    suspend fun getTasks(): List<Task>

    /**
     * Get conatct by id from database for UI.
     *
     * @param id
     * @return
     */
    suspend fun getTaskById(id:Int): Task

    /**
     * Delete task from database for UI.
     *
     * @param task
     */
    suspend fun deleteTask(task: Task)

    /**
     * Insert task from database for UI.
     *
     * @param contact
     */
    suspend fun insertTask(task: Task)

    /**
     * Update task from database for UI.
     *
     * @param task
     */
    suspend fun updateTask(task: Task)

}
