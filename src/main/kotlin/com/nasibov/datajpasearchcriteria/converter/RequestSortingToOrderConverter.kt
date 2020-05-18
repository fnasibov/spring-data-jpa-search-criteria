package com.nasibov.datajpasearchcriteria.converter

import com.nasibov.datajpasearchcriteria.model.SortOrder.ASC
import com.nasibov.datajpasearchcriteria.model.SortOrder.DESC
import com.nasibov.datajpasearchcriteria.model.Sorting
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Order
import javax.persistence.criteria.Path
import javax.persistence.criteria.Root

class RequestSortingToOrderConverter {
    companion object {
        fun <EntityType> convert(sorting: List<Sorting>, root: Root<EntityType>, criteriaBuilder: CriteriaBuilder): List<Order> {
            val orders: MutableList<Order> = ArrayList<Order>(sorting.size)
            for (sort in sorting) {
                val field: Path<Any> = root.get(sort.fieldName)
                when (sort.order) {
                    ASC -> orders.add(criteriaBuilder.asc(field))
                    DESC -> orders.add(criteriaBuilder.desc(field))
                }
            }

            return orders
        }
    }
}
