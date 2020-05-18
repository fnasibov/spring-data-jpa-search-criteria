package com.nasibov.datajpasearchcriteria.converter

import com.nasibov.datajpasearchcriteria.model.Filter
import com.nasibov.datajpasearchcriteria.model.FilterOperation.*
import com.nasibov.datajpasearchcriteria.utils.PredicateUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.criteria.*

@Component
class RequestFiltersToPredicateConverter(@Qualifier("converters") private val converters: Map<String, Converter<*>>) {

    @Suppress("UNCHECKED_CAST")
    fun <EntityType> convert(filters: List<Filter>, root: Root<EntityType>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val predicates: MutableList<Predicate> = ArrayList(filters.size)
        for (filter in filters) {
            val field: Path<out Comparable<*>> = root.get(filter.fieldName)
            val values: List<String> = filter.values
            require(values.isNotEmpty()) { "Values can not be empty" }
            when (filter.operation) {
                EQ -> predicates.add(criteriaBuilder.equal(field, converters[field.javaType.name]?.convert(values[0])))
                AL -> predicates.add(criteriaBuilder.like(field as Expression<String>, "%" + values[0] + "%"))
                BL -> predicates.add(criteriaBuilder.like(field as Expression<String>, values[0] + "%"))
                EL -> predicates.add(criteriaBuilder.like(field as Expression<String>, "%" + values[0]))
                NL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, "%" + values[0] + "%"))
                NBL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, values[0] + "%"))
                NEL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, "%" + values[0]))
                LT -> predicates.add(PredicateUtils.lessThan(field, criteriaBuilder, converters[field.javaType.name]?.convert(values[0])))
                GT -> predicates.add(PredicateUtils.greaterThan(field, criteriaBuilder, converters[field.javaType.name]?.convert(values[0])))
                LE -> predicates.add(PredicateUtils.lessOrEquals(field, criteriaBuilder, converters[field.javaType.name]?.convert(values[0])))
                GE -> predicates.add(PredicateUtils.greaterOrEquals(field, criteriaBuilder, converters[field.javaType.name]?.convert(values[0])))
                IN -> {
                    val inPredicate: CriteriaBuilder.In<Any> = criteriaBuilder.`in`(field)
                    converters[field.javaType.name]?.convertList(values)?.forEach { inPredicate.value(it) }
                    predicates.add(inPredicate)
                }
            }
        }
        if (predicates.isEmpty()) {
            return null
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }


}
