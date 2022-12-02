package com.example.taskapplicationnew.model.di

import android.content.Context
import androidx.room.Room
import com.example.taskapplicationnew.model.data.repository.RepositoryImpl
import com.example.taskapplicationnew.model.local.db.TaskDao
import com.example.taskapplicationnew.model.local.db.TaskDatabase
import com.example.taskapplicationnew.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesRoomDB(@ApplicationContext applicationContext: Context): TaskDao = Room.databaseBuilder(
        applicationContext,
        TaskDatabase::class.java, "note-database"
    ).build().taskDao()

    @Provides
    fun providesRepo(taskDao: TaskDao) : Repository = RepositoryImpl(taskDao)

}
