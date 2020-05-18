package com.nasibov.datajpasearchcriteria.model

data class Filter(
        var fieldName: String,
        var operation: FilterOperation,
        var values: List<String>
)
