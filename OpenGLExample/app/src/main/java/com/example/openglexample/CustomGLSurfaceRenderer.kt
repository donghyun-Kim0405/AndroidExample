package com.example.openglexample

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CustomGLSurfaceRenderer : GLSurfaceView.Renderer {

    private lateinit var triangle: Triangle

    /**
     * GLSurfaceView 생성시 최초 한번 호출 되는 메서드
     * OpenGL 환경설정, OpenGL그래픽 객체 초기화 수행
     * */
    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        triangle = Triangle()
        /**
         * color buffer clear 시 사용할 색상 지정
         * red, green, blue, alpha 순으로 0~1 사이의 값을 지정
         * 현재 검정색 화면 지정
         * */
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
    }

    /**
     * GLSurfaceView 다시 그려질 때 호출되는 메서드
     * */
    override fun onDrawFrame(gl10: GL10?) {
        /**
         * onSurfaceCreated의 glClearColor 에서 설정한 값으로 color buffer를 클리어
         * glClear메서드 사용하여 클리어할 수 있는 메서드는 다음과 같다.
         * 1. Color buffer (GL_COLOR_BUFFER_BIT)
         * 2. depth buffer (GL_DEPTH_BUFFER_BIT) -> 어떠한 물체의 픽셀들 다른 물체보다 앞에 있는지 판정하기 위해 사용
         * 3. stencil buffer (GL_STENCIL_BUFFER_BIT) -> 특정 픽셀들이 후면 버퍼에 기록되지 않도록 하는 버퍼, 특정 부분들이 랜더링 되지 않도록함
         * */
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        triangle.draw()
    }

    /**
     * GLSurfaceView의 크기 변경 혹은 디바이스 화면방향 전환 등으로 인해 GLSurfaceView의 geometry가 바뀔때 호출되는 메서드
     * */
    override fun onSurfaceChanged(gl10: GL10?, width: Int, height: Int) {
        /**
         * viewport를 설정 -> viewport : mobile browser에서 웹 컨텐츠를 출력하는 영역
         * viewport rectangle의 왼쪽 하단을 (0,0) 으로 지정
         * viewport의 width와 height를 지정
         * */
        GLES20.glViewport(0, 0, width, height)
    }

    companion object{
        fun loadShader(type: Int, shaderCode: String): Int {
            /**
             * 2가지 타입 중 하나로 shader객체 생성
             * vertex shader type(GLES20.GL_VERTEX_SHADER)
             * fragment shader type(GLES10.GL_FRAGMENT_SHADER)
             *  **/
            var shader: Int = GLES20.glCreateShader(type)

            /** shader객체에 shader source code 로드 **/
            GLES20.glShaderSource(shader, shaderCode)

            /** shader object compile **/
            GLES20.glCompileShader(shader)

            return shader
        }
    }
}