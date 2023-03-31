package com.example.treestructure.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.treestructure.App
import com.example.treestructure.data.dao.NodeDao
import com.example.treestructure.data.database.NodeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideNodeDatabase(context: Application): NodeDatabase {
        return Room.databaseBuilder(
            context,
            NodeDatabase::class.java,
            "NODE_DATABASE"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNodeDao(nodeDatabase: NodeDatabase): NodeDao {
        return nodeDatabase.nodeDao()
    }
}