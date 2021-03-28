package com.example.sampletodo.di

import android.content.Context
import androidx.room.Room
import com.example.sampletodo.data.MainRepository
import com.example.sampletodo.db.MainDatabase
import com.example.sampletodo.db.TaskDao
import com.example.sampletodo.db.TaskRepository
import com.example.sampletodo.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MainDatabase::class.java,
        AppConstants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun getDao(
        database: MainDatabase
    ) = database.taskDao()

    @Singleton
    @Provides
    fun getRepository(
        taskDao: TaskDao
    ) = MainRepository(taskDao) as TaskRepository
}