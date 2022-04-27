package com.example.hiltexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltexample.databinding.ActivityMainBinding
import com.example.hiltexample.qualifier.Test

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding

    @Inject internal lateinit var test : Test

    private val callback = object : CallbackInterface{
        override fun onSuccess(data: String) {
            binding.textView.text = data
        }
        override fun onFail() {
            Log.e("MainActivity", "FAIL")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLocal.setOnClickListener {
            viewModel.getLocalData(callback)
        }//get data from localDataSource

        binding.btnRemote.setOnClickListener {
            viewModel.getRemoteData(callback, binding.editText.text.toString())
        }//get data from RemoteDataSource

        binding.btnTest.setOnClickListener {
            test.printLog()
        }

    }//onCreate()

}
