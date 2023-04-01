package com.example.treestructure.di.module

import com.example.treestructure.data.mapper.NodeMapper
import com.example.treestructure.domain.util.HashNameGenerator
import dagger.Module
import dagger.Provides
import org.mapstruct.factory.Mappers

@Module
class AppModule {
    @Provides
    fun provideNodeMapper(): NodeMapper {
        return Mappers.getMapper(NodeMapper::class.java)
    }
}
