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
    )], indices = [Index(value = ["parent_id"]), Index(value = ["id"], unique = true)],
    tableName = "node"
)
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var name: String? = null,

    var level: Int = 0,

    @ColumnInfo(name = "parent_id")
    var parentId: Long? = null,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "created_at")
    var createdAt: Date? = null,
)