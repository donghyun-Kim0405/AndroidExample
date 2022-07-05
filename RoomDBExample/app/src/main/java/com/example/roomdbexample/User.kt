package com.example.roomdbexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    var name: String,
    var age: String,
    var phone: String,
    var items: List<TestData>
    ){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    //auto generated -> 자동으로 아이디 부여
}