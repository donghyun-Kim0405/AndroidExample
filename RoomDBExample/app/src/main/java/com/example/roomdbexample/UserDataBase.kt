package com.example.roomdbexample

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.*

//@Database(entities = [User::class, Student::class], version = 1)  이처럼 Student 테이블을 추가할 수 있다.

@Database(entities = [User::class], version = 1)
@TypeConverters(Converter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user-database"
                    ).build()
                }
            }
            return instance
        }
    }
}

