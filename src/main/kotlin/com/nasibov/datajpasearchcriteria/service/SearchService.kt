package com.nasibov.datajpasearchcriteria.service

import com.nasibov.datajpasearchcriteria.model.Request
import com.nasibov.datajpasearchcriteria.model.SearchResponse

interface SearchService {
    fun <ResultModelType> search(request: Request, entityClass: Class<ResultModelType>): SearchResponse<ResultModelType>
}
