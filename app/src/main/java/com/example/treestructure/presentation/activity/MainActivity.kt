package com.example.treestructure.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.treestructure.App
import com.example.treestructure.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (this.application as App).appComponent.inject(this)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController.also {
            it.navigate(R.id.action_global_nodeFragment)
        }
    }
}