package com.example.taskapplicationnew.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapplicationnew.model.data.Task
import com.example.taskapplicationnew.model.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Task view model.
 *
 * @property repository
 * @constructor Create empty Dashboard view model
 */
@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {
    private val _taskList: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val taskList = _taskList.asStateFlow()

    /**
     * Get tasks.
     *
     */
    fun getTasks() =
        viewModelScope.launch {
            _taskList.value = repository.getTasks()
        }

    /**
     * Add task new.
     *
     * @param task
     */
    fun addTask(
        task:Task
    ) {
        viewModelScope.launch {
            repository.insertTask(task)
            println("$task ADDED TO DB")
        }
    }

    /**
     * Delete task.
     *
     * @param task
     */
    suspend fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    /**
     * Companion.
     *
     * @constructor Create empty Companion
     */
    companion object {
        const val TAG = "DashboardVIEWMODEL"
    }
}
