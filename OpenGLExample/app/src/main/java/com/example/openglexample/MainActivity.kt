package com.example.openglexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.openglexample.databinding.ActivityMainBinding

/**
 * flow - create shape on the GLSurfaceView
 *
 * 1. Manifest.xml에 feature작성
 * 2. xml에 GLSurfaceView 추가
 * 3. GLSurfaceView.Renderer 정의
 * 4. 만들고자하는 shape정의 (예 - Triangle.class)
 * 5. Shape을 그리기 위한 코드
 *      A. VertextShader - shape의 vertex를 랜더링하기 위한 OpenGL ES 그래픽스 코드
 *      B. Fragment Shader - shape의 색 또는 텍스처를 랜더링 하기위한 OpenGL ES 그래픽스 코드
 *      C. Program - Shape를 그리기 위해 사용되는 Shader를 포함하는 OpenGL ES객체
 *
 * 6. Renderer class에 loadShader함수 추가 (Renderer class)
 * 7. shader코드 컴파일 후 -> 결과물 -> OpenGL ES program객체에 추가 (Triangle class)
 * 8. Triangle class에 draw라는 이름의 메서드 추가 <- 실제 Shape를 그리기 위한 처리
 * 9. 화면내 삼각형 그리기 -> CustomRenderer의 onDrawFrame() 메서드내에서 Triangle class의 draw()메서드 호출
 *
 *
 * */


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var renderer: CustomGLSurfaceRenderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.glSurfaceView.setEGLContextClientVersion(2)
        renderer = CustomGLSurfaceRenderer()
        binding.glSurfaceView.setRenderer(renderer)



    }//onCreate()

}