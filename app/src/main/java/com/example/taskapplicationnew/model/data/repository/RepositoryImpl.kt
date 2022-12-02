package com.example.taskapplicationnew.model.data.repository

import com.example.taskapplicationnew.model.data.Task
import com.example.taskapplicationnew.model.local.db.TaskDao
import com.example.taskapplicationnew.repository.Repository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository impl extends form Repository.
 *
 * @property phoneDao
 * @constructor Create empty Repositoy operating with TaskDao
 */
class RepositoryImpl @Inject constructor(private val taskDao: TaskDao) : Repository {


    override suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO) {
        return@withContext taskDao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task = withContext(Dispatchers.IO) {
        taskDao.getTaskById(id)
    }

    override suspend fun deleteTask(task: Task) = withContext(Dispatchers.IO){
        taskDao.deleteTask(task)
    }

    override suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) = withContext(Dispatchers.IO) {
        taskDao.updateTask(task)
    }
}
