package com.example.hiltexample.qualifier

import javax.inject.Inject

class Test @Inject constructor(
    @TestModule.TestTypeA private val testA : TestInterface,
    @TestModule.TestTypeB private val testB : TestInterface
){
    public fun printLog(){
        testA.printLog()
        testB.printLog()
    }
}