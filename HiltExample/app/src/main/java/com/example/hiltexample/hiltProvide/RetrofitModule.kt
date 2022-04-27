package com.example.hiltexample.hiltProvide

import android.content.Context
import com.example.hiltexample.MainRepository
import com.example.hiltexample.RetrofitAPI
import com.example.hiltexample.hiltBind.LocalDataInterface
import com.example.hiltexample.hiltBind.LocalDataSource
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/*@Module
@InstallIn(ActivityComponent::class)
abstract class hiltModule{
    @Binds
    abstract fun bindLocalDataSource(
        impl : LocalDataSource
    ) : LocalDataInterface

 */

    @InstallIn(ActivityComponent::class)
    @Module
    object RetrofitModule {

        @Provides
        fun provideBaseUrl() = "http://192.168.0.10:8080"

        @Provides
        fun provideRetrofit(@ApplicationContext context : Context) : Retrofit {
            return Retrofit.Builder().baseUrl(provideBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
        }

        @Provides
        fun provideRetrofitAPI(retrofit: Retrofit) : RetrofitAPI {
            return retrofit.create(RetrofitAPI::class.java)
        }

        fun provideMainRepository(retrofitAPI : RetrofitAPI): MainRepository {
            return MainRepository(retrofitAPI) // <- 이 경우엔 MainReposioty클래스 생성자에 @Inject constructor(private val retrofitAPI : RetrofitAPI) 선언
            //생성자 주입 방식

            /*return MainRepository().apply {
                this.retrofitAPI = retrofitAPI
            }   //필드 주입 방식*/
        }

    }
//}
