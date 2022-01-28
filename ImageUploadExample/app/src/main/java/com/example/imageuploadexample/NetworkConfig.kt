package com.example.imageuploadexample

class NetworkConfig private constructor(){

    companion object{
        private val ip = "http://192.168.45.118:8080"
        public fun getIP() = ip
    }
}