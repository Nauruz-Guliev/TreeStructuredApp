package com.example.treestructure.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.treestructure.domain.viewmodel.ViewModelFactory
import com.example.treestructure.domain.viewmodel.ViewModelKey
import com.example.treestructure.presentation.fragment.node.NodeViewModel
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
}