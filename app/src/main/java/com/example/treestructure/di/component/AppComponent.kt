package com.example.treestructure.di.component

import android.app.Application
import com.example.treestructure.di.module.AppModule
import com.example.treestructure.di.module.DatabaseModule
import com.example.treestructure.di.module.RepositoryModule
import com.example.treestructure.di.module.ViewModelModule
import com.example.treestructure.presentation.activity.MainActivity
import com.example.treestructure.presentation.fragment.node.NodeFragment
import com.example.treestructure.presentation.fragment.root.RootNodeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
    fun inject(activity: MainActivity)
    fun inject(fragment: NodeFragment)
    fun inject(fragment: RootNodeFragment)
}
