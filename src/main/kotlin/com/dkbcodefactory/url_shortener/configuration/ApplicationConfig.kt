package com.dkbcodefactory.url_shortener.configuration

import com.dkbcodefactory.url_shortener.repository.CassandraUrlRepository
import com.dkbcodefactory.url_shortener.repository.InMemoryUrlRepository
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import com.dkbcodefactory.url_shortener.service.generator.HashCodeGenerator
import com.dkbcodefactory.url_shortener.service.generator.RandomHashCodeGenerator
import com.dkbcodefactory.url_shortener.service.generator.Sha256HashCodeGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ApplicationConfig {

    @Bean
    @Profile("dev")
    fun randomHashCodeGenerator(): HashCodeGenerator {
        return RandomHashCodeGenerator()
    }

    @Bean
    @Profile("dev")
    fun inMemoryUrlRepository(): UrlRepository {
        return InMemoryUrlRepository()
    }

    @Bean
    @Profile("prod")
    fun sha256HashCodeGenerator(): HashCodeGenerator {
        return Sha256HashCodeGenerator()
    }

    @Bean
    @Profile("prod")
    fun cassandraUrlRepository(): UrlRepository {
        return CassandraUrlRepository()
    }
}