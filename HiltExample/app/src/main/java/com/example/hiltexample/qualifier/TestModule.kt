package com.example.hiltexample.qualifier

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object TestModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TestTypeA

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TestTypeB

    @TestTypeA
    @Provides
    fun testProvidesA() : TestInterface{
        return TestA()
    }

    @TestTypeB
    @Provides
    fun testProvidesB() : TestInterface{
        return TestB()
    }


}