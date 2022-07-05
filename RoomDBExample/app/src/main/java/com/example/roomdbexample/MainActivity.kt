package com.example.roomdbexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.RoomDatabase
import com.example.roomdbexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val roomDB by lazy { UserDatabase.getInstance(applicationContext) }

    private var name: String? = null
    private var age: String? = null
    private var phone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsert.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                insertUserData(createUserData())
            }

        }
        binding.btnSearch.setOnClickListener {
            startActivity(
                Intent(this, SearchActivity::class.java)
            )
        }


    }
    private fun createUserData() : User{
        name = binding.editName.text.toString()
        age = binding.editAge.text.toString()
        phone = binding.editPhone.text.toString()

        return User(name!!, age!!, phone!!, createTestData())
    }

    private fun createTestData() : List<TestData>{
        val temp = ArrayList<TestData>()
        for (i in 0..100) {
            temp.add(TestData(i.toString()))
        }
        return temp as List<TestData>
    }

    private fun insertUserData(user: User){
        roomDB?.let {
            it.userDao().insert(user)
        }
    }


}