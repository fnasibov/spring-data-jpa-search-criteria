package com.nasibov.datajpasearchcriteria.model

data class Request(
        val filters: List<Filter>,
        val sorting: List<Sorting>,
        val page: Int,
        val pageSize: Int
)
