package com.nasibov.datajpasearchcriteria.converter.impl

import com.nasibov.datajpasearchcriteria.converter.Converter
import org.springframework.stereotype.Component

@Component
class StringToLongConverter : Converter<Long> {

    override fun convert(value: String): Long {
        return value.toLong()
    }

}
