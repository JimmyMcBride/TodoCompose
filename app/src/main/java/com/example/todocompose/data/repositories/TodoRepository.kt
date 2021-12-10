package com.example.todocompose.data.repositories

import com.example.todocompose.data.TodoDao
import com.example.todocompose.data.models.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {
    val getAllTasks = todoDao.getAllTasks()
    val sortByLowPriority = todoDao.sortByLowPriority()
    val sortByHighPriority = todoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int) = todoDao.getSelectedTask(taskId)

    suspend fun addTask(todoTask: TodoTask) = todoDao.addTask(todoTask)

    suspend fun updateTask(todoTask: TodoTask) = todoDao.updateTask(todoTask)

    suspend fun deleteTask(todoTask: TodoTask) = todoDao.deleteTask(todoTask)

    suspend fun deleteAllTasks() = todoDao.deleteAllTasks()

    fun searchDatabase(searchQuery: String) = todoDao.searchDatabase(searchQuery)
}