package com.nasibov.datajpasearchcriteria.model


data class SearchResponse<ResultType> (
        val result: List<ResultType>,
        val page: Int,
        val pageSize: Int,
        val count: Long
)

