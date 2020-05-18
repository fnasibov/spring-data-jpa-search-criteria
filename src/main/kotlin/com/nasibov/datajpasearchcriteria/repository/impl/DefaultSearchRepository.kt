package com.nasibov.datajpasearchcriteria.repository.impl

import com.nasibov.datajpasearchcriteria.converter.RequestFiltersToPredicateConverter
import com.nasibov.datajpasearchcriteria.converter.RequestSortingToOrderConverter
import com.nasibov.datajpasearchcriteria.model.Request
import com.nasibov.datajpasearchcriteria.repository.SearchRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class DefaultSearchRepository(
        private val requestFiltersToPredicateConverter: RequestFiltersToPredicateConverter
) : SearchRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun <EntityType> search(entityClass: Class<EntityType>, request: Request): List<EntityType> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(entityClass)
        val root = criteriaQuery.from(entityClass)
        val filter = requestFiltersToPredicateConverter.convert(request.filters, root, criteriaBuilder)
        criteriaQuery.where(filter)
        val order = RequestSortingToOrderConverter.convert(request.sorting, root, criteriaBuilder)
        criteriaQuery.orderBy(order)
        val query = entityManager.createQuery(criteriaQuery)
        if (request.page > 1) {
            query.firstResult = (request.page - 1) * request.pageSize
        }
        query.maxResults = request.pageSize

        return query.resultList
    }

    override fun <EntityType> count(entityClass: Class<EntityType>, request: Request): Long {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(entityClass)
        val filter = requestFiltersToPredicateConverter.convert(request.filters, root, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        criteriaQuery.where(filter)
        val query = entityManager.createQuery(criteriaQuery)

        return query.singleResult
    }

}
