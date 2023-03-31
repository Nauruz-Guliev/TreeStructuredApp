package com.example.treestructure.data.mapper

import com.example.treestructure.data.entity.NodeEntity
import com.example.treestructure.presentation.model.Node
import org.mapstruct.Mapper

@Mapper
interface NodeMapper {
    fun convertToEntity(model: Node): NodeEntity
    fun convertToModel(entity: NodeEntity): Node
}