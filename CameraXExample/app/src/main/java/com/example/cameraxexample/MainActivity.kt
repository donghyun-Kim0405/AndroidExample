package com.example.cameraxexample

import android.Manifest
import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.*
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cameraxexample.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.BitmapFactory

import android.graphics.ImageFormat

import android.graphics.YuvImage
import android.media.Image.Plane
import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.PixelFormat.RGBA_8888
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888
import androidx.core.math.MathUtils.clamp


/**
 FLOW: getPermission -> startCamera() -> ExecutorService수행
**/

typealias LumaListener = (luma: Bitmap?) -> Unit

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private var imageCapture : ImageCapture? = null
    private var videoCapture: VideoCapture? = null
    private var recording : Recording? = null

    private lateinit var cameraExecutors: ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (allPermissionGranted()) {
            startCamera()
        }else{
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
        cameraExecutors = Executors.newSingleThreadExecutor()


    }//onCreate()

    override fun onDestroy() {  //비동기 실행 executor종료
        super.onDestroy()
        cameraExecutors.shutdown()
    }//onDestroy()

    private fun captureVideo(){
        val videoCapture = this.videoCapture ?: return

    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.preview.surfaceProvider)
                }

            val imageCapture = ImageCapture.Builder()
                .build()
            val imageAnalyzer = ImageAnalysis.Builder()
                .setOutputImageFormat(OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutors, LuminosityAnalyzer { luma ->
                        Log.d(TAG, "Average luminosity: $luma")
                        runOnUiThread { binding.imageView.setImageBitmap(luma) }
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll() //rebind 경우를 위해 unbind수행
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, imageAnalyzer) //bind수행
            } catch (e: Exception) {
                Log.e(TAG, "USE case binding failed", e)
            }
        }, ContextCompat.getMainExecutor(this))

    }


    private fun allPermissionGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all{
            ContextCompat.checkSelfPermission(
                baseContext, it) == PackageManager.PERMISSION_GRANTED
        }
    }
    //-------------------------------------------------------------------------------------------


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionGranted()) {
                startCamera()
            }
        }else{
            Toast.makeText(this, "need to permission", Toast.LENGTH_SHORT).show()
            finish()
        }

    }//onRequestPermissionResult

    //-------------------------------------------------------------------------------------------
    companion object{
        private const val TAG = "CameraXApp"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()    //list to array
    }
}   //MainActivity

private class LuminosityAnalyzer(private val listener: LumaListener) : ImageAnalysis.Analyzer {
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {   //work for each frame here
        val buffer = image.planes[0].buffer
        val data = buffer.toByteArray()
        val pixels = data.map { it.toInt() and 0xFF }
        val luma = pixels.average()

        /*val bitmap: Bitmap? = image.image?.toBitmap()
        listener(bitmap)*/
        listener(image.toBitmap())
        //listener(image.toBitmap())

        image.close()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun ImageProxy.toBitmap() : Bitmap{
        val data = planes[0].buffer.toByteArray()
        val pixels = IntArray(data.size / planes[0].pixelStride) {
            var index = it * planes[0].pixelStride
            (data[index++].toInt() and 0xff.shl(16)) or
                    (data[index++].toInt() and 0xff).shl(8) or
                    (data[index++].toInt() and 0xff).shl(0) or
                    (data[index].toInt() and 0xff).shl(24)
        }
        return Bitmap.createBitmap(
            pixels,
            0,
            planes[0].rowStride / planes[0].pixelStride,
            width,
            height,
            Bitmap.Config.RGBA_F16
        )
    }//toBitmap


}

