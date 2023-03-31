package com.example.treestructure.data.entity

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.example.treestructure.data.utils.converter.DateConverter
import java.util.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = NodeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parent_id"),
        onDelete = CASCADE
    )], indices = [Index(value = ["parent_id"])],
    tableName = "node"
)
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String? = null,

    val level: Int = 0,

    @ColumnInfo(name = "parent_id")
    val parentId: Long? = null,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "created_at")
    val createdAt: Date? = null ,
)