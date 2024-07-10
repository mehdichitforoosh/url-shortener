package com.dkbcodefactory.url_shortener.configuration

import com.dkbcodefactory.url_shortener.service.RandomShortUrlGenerator
import com.dkbcodefactory.url_shortener.service.ShortUrlGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    private fun randomShortUrlGenerator(): ShortUrlGenerator {
        return RandomShortUrlGenerator()
    }
}