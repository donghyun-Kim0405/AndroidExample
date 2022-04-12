package com.example.applicationclassexample

import android.app.Application
import android.widget.Toast

class CustomApplication : Application(){

    private var message : String? = null

    public fun setMessage(message : String){
        this.message = message
    }

    public fun getId() : String?{
        return this.message
    }

    public fun showMessage(){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}