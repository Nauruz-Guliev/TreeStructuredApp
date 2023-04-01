package com.example.treestructure.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.treestructure.di.viewmodel.ViewModelFactory
import com.example.treestructure.di.viewmodel.ViewModelKey
import com.example.treestructure.presentation.fragment.node.NodeViewModel
import com.example.treestructure.presentation.fragment.root.RootNodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NodeViewModel::class)
    abstract fun nodeViewModel(viewModel: NodeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RootNodeViewModel::class)
    abstract fun rootNodeViewModel(viewModel: RootNodeViewModel): ViewModel
}
