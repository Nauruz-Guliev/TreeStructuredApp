package com.example.treestructure.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.treestructure.data.dao.NodeDao
import com.example.treestructure.data.entity.NodeEntity
import com.example.treestructure.data.utils.converter.DateConverter


@Database(entities = [NodeEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NodeDatabase : RoomDatabase(){
    abstract fun nodeDao(): NodeDao
}