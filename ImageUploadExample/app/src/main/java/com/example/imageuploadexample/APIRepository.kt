package com.example.imageuploadexample

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository(context : Context){

    private lateinit var fileManager: FileManager
    private lateinit var BASEURL : String
    private val TAG : String = "APIRepository"

    init {
        BASEURL = NetworkConfig.getIP()
        fileManager = FileManager(context)
    }

    public fun uploadImage(image : Bitmap){

        //retrofit갤러리로 부터 선택한 비트맵을 파일형태로 특정 경로에 저장 -> retrofit을 통해 이미지를 업로드할때 사용
        fileManager.createImageFile(image)
        val fileName : String = "testImage"

        var requestBody : RequestBody = RequestBody.create(MediaType.parse("image/*"), fileManager.getFile())
        var body : MultipartBody.Part = MultipartBody.Part.createFormData("image", fileName, requestBody)

        var gson : Gson =  GsonBuilder()
            .setLenient()
            .create()

        var retrofit =
            Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        var api = retrofit.create(APIService::class.java)
        val call = api.getUploadImageCall(body)
        call.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e(TAG, response.body().toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })




    }

}