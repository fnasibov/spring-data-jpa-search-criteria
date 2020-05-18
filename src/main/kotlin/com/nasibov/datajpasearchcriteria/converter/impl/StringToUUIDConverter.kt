package com.nasibov.datajpasearchcriteria.converter.impl

import com.nasibov.datajpasearchcriteria.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

@Component
class StringToUUIDConverter : Converter<UUID> {

    override fun convert(value: String): UUID {
        return UUID.fromString(value)
    }

}
