package com.dkbcodefactory.url_shortener.configuration

import com.dkbcodefactory.url_shortener.repository.InMemoryUrlRepository
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import com.dkbcodefactory.url_shortener.service.generator.RandomShortUrlGenerator
import com.dkbcodefactory.url_shortener.service.generator.Sha256ShortUrlGenerator
import com.dkbcodefactory.url_shortener.service.generator.ShortUrlGenerator
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(name = ["services.short-url-generator"], havingValue = "random")
    fun randomShortUrlGenerator(): ShortUrlGenerator {
        return RandomShortUrlGenerator()
    }

    @Bean
    @ConditionalOnProperty(name = ["services.short-url-generator"], havingValue = "sha-256")
    fun sha256ShortUrlGenerator(): ShortUrlGenerator {
        return Sha256ShortUrlGenerator()
    }

    @Bean
    @ConditionalOnProperty(name = ["repositories.url-repository"], havingValue = "in-memory")
    fun inMemoryUrlRepository(): UrlRepository {
        return InMemoryUrlRepository()
    }
}