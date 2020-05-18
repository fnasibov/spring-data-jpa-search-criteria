package com.nasibov.datajpasearchcriteria.repository

import com.nasibov.datajpasearchcriteria.model.Request

interface SearchRepository {
    fun <EntityType> search(entityClass: Class<EntityType>, request: Request): List<EntityType>
    fun <EntityType> count(entityClass: Class<EntityType>, request: Request): Long
}
