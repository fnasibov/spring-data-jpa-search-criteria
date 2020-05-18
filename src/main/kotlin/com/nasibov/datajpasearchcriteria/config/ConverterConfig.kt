package com.nasibov.datajpasearchcriteria.config

import com.nasibov.datajpasearchcriteria.converter.Converter
import org.springframework.beans.factory.getBeansOfType
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.criteria.Predicate

@Configuration
@ConditionalOnClass(Predicate::class, EntityManager::class)
@AutoConfigureAfter(JpaRepositoriesAutoConfiguration::class)
@ComponentScan("com.nasibov.datajpasearchcriteria")
class ConverterConfig(private val context: ApplicationContext ) {

    @Bean("converters")
    fun convertersMap(): Map<String, Converter<*>> {
        val converters: MutableMap<String, Converter<*>> = mutableMapOf()
        val beansOfType = context.getBeansOfType<Converter<*>>()
        beansOfType.forEach { (_, converter) ->
            val type: String = (converter.javaClass.genericInterfaces.first() as ParameterizedType)
                    .actualTypeArguments.first().typeName
            converters[type] = converter
        }

        return converters
    }
}
