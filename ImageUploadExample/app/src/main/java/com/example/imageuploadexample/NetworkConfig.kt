package com.example.imageuploadexample

class NetworkConfig private constructor(){

    companion object{
        private val ip = "http://172.30.1.22:8080"
        public fun getIP() = ip
    }
}