package com.example.imageuploadexample

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import okhttp3.MediaType
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class FileManager(context : Context){

    private val TAG : String = "FileWriteHelper"
    private lateinit var BASEURL : String

    init {
        BASEURL = context.getFilesDir().toString() +"/images.jpg"
    }

    public fun getBASEURL() : String = BASEURL

    fun createImageFile(image: Bitmap) {
        var file : File = File(BASEURL)
        var out : OutputStream? = null

        try{
            file.createNewFile()
            out = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }catch (e : Exception){
            Log.e(TAG, e.message.toString())
        }finally {
            out?.close()
        }
    }

    fun getFile(): File {
        val file = File(BASEURL)
        return file
    }

}