package com.example.viewmodelexample.instanceFactory

import androidx.lifecycle.ViewModel


// andorid가 기본적으로 제공해주는 factory 클래스
class InstanceFactoryViewModel : ViewModel(){

    public fun getData() = "instancefactoryViewModel data"

}
