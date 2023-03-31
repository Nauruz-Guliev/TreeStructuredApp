package com.example.treestructure

import android.app.Application
import com.example.treestructure.di.component.AppComponent
import com.example.treestructure.di.component.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}