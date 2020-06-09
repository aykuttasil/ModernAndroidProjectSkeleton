package com.aykuttasil.data.di

import com.aykuttasil.data.repositories.UserRepositoryImpl
import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.network.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
  includes = [
    DatabaseModule::class,
    NetworkModule::class,
    ApiModule::class
  ]
)
class DataModule {

  @Singleton
  @Provides
  fun provideUserRepository(
    roomUserDataStore: RoomUserDataStore,
    inMemoryUserDataStore: InMemoryUserDataStore,
    remoteUserDataStore: RemoteUserDataStore
  ): UserRepository {
    return UserRepositoryImpl(roomUserDataStore, inMemoryUserDataStore, remoteUserDataStore)
  }
}