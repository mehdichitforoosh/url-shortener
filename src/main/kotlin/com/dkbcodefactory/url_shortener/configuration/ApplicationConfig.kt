package com.dkbcodefactory.url_shortener.configuration

import com.dkbcodefactory.url_shortener.repository.CassandraUrlRepository
import com.dkbcodefactory.url_shortener.repository.InMemoryUrlRepository
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import com.dkbcodefactory.url_shortener.service.generator.RandomShortUrlGenerator
import com.dkbcodefactory.url_shortener.service.generator.Sha256ShortUrlGenerator
import com.dkbcodefactory.url_shortener.service.generator.ShortUrlGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ApplicationConfig {

    @Bean
    @Profile("dev")
    fun randomShortUrlGenerator(): ShortUrlGenerator {
        return RandomShortUrlGenerator()
    }

    @Bean
    @Profile("dev")
    fun inMemoryUrlRepository(): UrlRepository {
        return InMemoryUrlRepository()
    }

    @Bean
    @Profile("prod")
    fun sha256ShortUrlGenerator(): ShortUrlGenerator {
        return Sha256ShortUrlGenerator()
    }

    @Bean
    @Profile("prod")
    fun cassandraUrlRepository(): UrlRepository {
        return CassandraUrlRepository()
    }
}