package com.example.hiltexample.hiltBind

import com.example.hiltexample.hiltBind.LocalDataInterface
import com.example.hiltexample.hiltBind.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class LocalDataModule {

    @Binds
    abstract fun bindLocalDataSource(impl : LocalDataSource) : LocalDataInterface
    // parameter -> interface구현체    &   return value -> interface

}