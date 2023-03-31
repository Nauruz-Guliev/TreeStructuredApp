package com.example.treestructure.di.module

import com.example.treestructure.data.repository.NodesRepositoryImpl
import com.example.treestructure.domain.repository.NodesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNodeRepository(nodesRepositoryImpl: NodesRepositoryImpl): NodesRepository
}