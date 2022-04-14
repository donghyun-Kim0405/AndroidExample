package com.example.manualdependecyinjectionexample

import java.util.*

interface Factory<T> {
    fun create() : T
}