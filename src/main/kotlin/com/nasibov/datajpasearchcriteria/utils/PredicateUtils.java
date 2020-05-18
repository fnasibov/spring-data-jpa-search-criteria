package com.nasibov.datajpasearchcriteria.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PredicateUtils {
    public static Predicate lessThan(Path<? extends Comparable> field, CriteriaBuilder criteriaBuilder, Object convert) {
        return criteriaBuilder.lessThan(field, (Comparable) convert);
    }

    public static Predicate greaterThan(Path<? extends Comparable> field, CriteriaBuilder criteriaBuilder, Object convert) {
        return criteriaBuilder.greaterThan(field, (Comparable) convert);
    }

    public static Predicate lessOrEquals(Path<? extends Comparable> field, CriteriaBuilder criteriaBuilder, Object convert) {
        return criteriaBuilder.lessThanOrEqualTo(field, (Comparable) convert);
    }

    public static Predicate greaterOrEquals(Path<? extends Comparable> field, CriteriaBuilder criteriaBuilder, Object convert) {
        return criteriaBuilder.greaterThanOrEqualTo(field, (Comparable) convert);
    }
}
