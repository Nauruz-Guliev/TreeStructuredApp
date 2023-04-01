package com.example.treestructure.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.treestructure.data.dao.NodeDao
import com.example.treestructure.data.database.NodeDatabase
import com.example.treestructure.data.entity.NodeEntity
import com.example.treestructure.domain.util.Constants
import com.example.treestructure.presentation.utils.HashNameGenerator
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar.getInstance
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Volatile
    var DB_INSTANCE: NodeDatabase? = null

    @Provides
    @Singleton
    fun provideNodeDatabase(
        context: Application,
        hashNameGenerator: HashNameGenerator
    ): NodeDatabase {
        return DB_INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context, NodeDatabase::class.java, "NODES_DATABASE"
            ).addCallback(
                NodeDatabaseDatabaseCallback(
                    CoroutineScope(Dispatchers.IO),
                    hashNameGenerator
                )
            ).build()
                .also { nodeDb ->
                    DB_INSTANCE = nodeDb
                }
        }
    }

    /**
     * Класс используется для создания верхушки дерева (Root)
     */

    private inner class NodeDatabaseDatabaseCallback(
        private val scope: CoroutineScope,
        private val generator: HashNameGenerator
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            DB_INSTANCE!!.let { database ->
                scope.launch {
                    NodeEntity(
                        id = Constants.ROOT_NODE_ID,
                        name = null,
                        level = 0,
                        parentId = null,
                        createdAt = getInstance().time
                    ).also { node ->
                        node.name = generator.generate(node.hashCode())
                    }.also { node ->
                        database.nodeDao().create(node)
                    }
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideNodeDao(nodeDatabase: NodeDatabase): NodeDao {
        return nodeDatabase.nodeDao()
    }
}
