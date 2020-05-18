package com.nasibov.datajpasearchcriteria.service.impl

import com.nasibov.datajpasearchcriteria.model.Request
import com.nasibov.datajpasearchcriteria.model.SearchResponse
import com.nasibov.datajpasearchcriteria.repository.SearchRepository
import com.nasibov.datajpasearchcriteria.service.SearchService
import net.bytebuddy.description.method.MethodDescription
import org.springframework.stereotype.Service

@Service
class DefaultSearchService(private val searchRepository: SearchRepository) : SearchService {

    override fun <ResultModelType> search(request: Request, entityClass: Class<ResultModelType>): SearchResponse<ResultModelType> {
        val search: List<ResultModelType> = searchRepository.search(entityClass, request)
        val count: Long = searchRepository.count(entityClass, request)

        return SearchResponse(search, request.page, request.pageSize, count)
    }


}
