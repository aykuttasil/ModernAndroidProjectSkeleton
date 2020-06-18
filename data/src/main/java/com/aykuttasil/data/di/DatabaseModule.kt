package com.aykuttasil.data.di

import android.content.Context
import androidx.room.Room
import com.aykuttasil.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

  private const val DB_NAME = "a2a_5.db"

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      DB_NAME
    ).fallbackToDestructiveMigration().build()

  @Provides
  @Singleton
  fun provideUserDao(db: AppDatabase) = db.getUserDao()
}
