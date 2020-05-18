package com.nasibov.datajpasearchcriteria.converter

interface Converter<ResultType> {
    fun convertList(values: List<String>): List<ResultType> {
        return values.map { convert(it) }
    }
    fun convert(value: String): ResultType
}
