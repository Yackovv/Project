package com.example.project

import android.app.Application
import com.example.project.di.DaggerApplicationComponent

class ProjectApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}