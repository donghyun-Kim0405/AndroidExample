package com.example.openglexample

import android.opengl.GLES20
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * OpenGL ES에서는 좌표계(Coordinate system)의 원점을 (0,0,0)을 GLSurfaceView의 중앙으로 하는것이 default이다.
 * 3차원 좌표(x, y, z)를 사용하며 좌표 (1,1,0)은 우상단 (-1,-1,0)은 좌측하단이 된다.
 * 각각의 vertex선언은 상단부터 반시계방향 순서로 선언한다.
 *
 * 현재 정의한 클래스는 renderer의 onSurfaceCreated()에서 호출되어야 한다.
 * **/


class Triangle {
    /** float buffer type으로 vertexbuffer 선언 **/
    private lateinit var vertexBuffer: FloatBuffer

    /** float 배열에 삼각형의 vertex를 위한 좌표지정 **/
    private val COORDS_PER_VERTEX = 3

    private val triangleCoords = floatArrayOf(
        0.0f, 0.622008459f, 0.0f, //상단 vertex : 꼭짓점
        -0.5f, -0.311004243f, 0.0f, //좌측 하단
        0.5f, -0.311004243f, 0.0f   //우측 하단
    )
    /** red, green, blue, alpha값 저장 **/
    val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    private var mProgram: Int = 0

    private var mPositionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4    //4 byte per vertex

    //========================================================================================================================

    init {
        /** ByteBuffer할당 **/
        var byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(triangleCoords.size * 4)

        /**
         *  ByteBuffer에서 사용할 엔디안 지정
         *  Buffer의 byte order로 디바이스 하드웨어의 native byte order사용
         * **/
        byteBuffer.order(ByteOrder.nativeOrder())

        /** bytebuffer -> FloatBuffer로 변환
         *  이유 : ByteBuffer이용시 성능이 개선되므로 ->  bytebuffer로 생성후 변환하는 과정 수행
         * **/
        vertexBuffer = byteBuffer.asFloatBuffer()

        /** float 배열에 정의된 좌표를 FloatBuffer에 저장 **/
        vertexBuffer.put(triangleCoords)

        /** 읽어올 버퍼의 위치를 0으로 설정 -> 첫번째 좌표부터 읽어온다. **/
        vertexBuffer.position(0)

        val vertexShader: Int = CustomGLSurfaceRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)

        val fragmentShader: Int = CustomGLSurfaceRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        mProgram = GLES20.glCreateProgram()

        /** vertex shader를 program 객체에 추가 **/
        GLES20.glAttachShader(mProgram, vertexShader)

        /** fragment shader를 program 객체에 추가 **/
        GLES20.glAttachShader(mProgram, fragmentShader)

        /** program 객체를 OpenGL에 연결 program에 추가된 shader들이 OpenGL에 연결 **/
        GLES20.glLinkProgram(mProgram)
    }//init

    //========================================================================================================================

    public fun draw(){
        Log.e("Triangle", "draw called")
        /** 랜더링 상태(Rendering State)의 일부분으로 program추가 **/
        GLES20.glUseProgram(mProgram)

        /** program object로 부터 vertex shader의 vPosition 멤버에 대한 핸들을 가져옴 **/
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")

        /** triangle vertex속성 활성화 <- 수행해야 랜더링시 반영 **/
        GLES20.glEnableVertexAttribArray(mPositionHandle)

        /** triangle vertex 속성을 vertexBuffer에 저장되어 있는 좌표들로 정의 **/
        GLES20.glVertexAttribPointer(
            mPositionHandle,
            COORDS_PER_VERTEX,
            GLES20.GL_FLOAT,
            false,
            vertexStride,
            vertexBuffer
        )

        /** program 객체로부터 fragment shader의 vColor member에 대한 핸들을 가져옴 **/
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")

        /** triangle 랜더링시 사용할 색으로 color변수에 정의한 값을 사용 **/
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)

        /** vertex갯수만큼 triangle render **/
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        /** vertex 속성 비활성화 **/
        GLES20.glDisableVertexAttribArray(mPositionHandle)
    }




    companion object{

        /**
         * Shader는 OpenGL Shading Language(GLSL)를 사용하여 작성
         * OpenGL ES 환경에서 사용하기위해 이 코드들이 먼저 컴파일 되어야 하는데 이를 위해서 renderer class에 loadShader함수를 추가한다.
         *  **/
        private val vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}"

        private val fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}"

    }
}