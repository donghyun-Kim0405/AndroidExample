package com.example.imageuploadexample


import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface APIService {
    @Multipart
    @POST("/api/test/uploadImage")
    public fun getUploadImageCall(@Part image : MultipartBody.Part) : Call<ResponseBody>


    @Multipart
    @POST("/api/test/uploadString")
    public fun getTestCall(@Part("testData") param: RequestBody?): Call<ResponseBody>

}