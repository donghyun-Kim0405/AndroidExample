package com.example.manualdependecyinjectionexample

import android.app.Application
import com.example.manualdependecyinjectionexample.container.AppContainer

class CustomApplication : Application(){
    val appContainer : AppContainer = AppContainer()    //appContainer생성


}