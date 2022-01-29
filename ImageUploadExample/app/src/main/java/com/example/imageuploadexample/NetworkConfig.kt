package com.example.imageuploadexample

class NetworkConfig private constructor(){

    companion object{
        private val ip = "http://172.20.10.4:8080"
        public fun getIP() = ip
    }
}