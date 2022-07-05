package com.example.roomdbexample

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {
    @TypeConverter
    public fun listToJson(value: List<TestData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<TestData>::class.java).toList()


}