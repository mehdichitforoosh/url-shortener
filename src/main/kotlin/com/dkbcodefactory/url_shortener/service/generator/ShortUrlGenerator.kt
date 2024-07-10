package com.dkbcodefactory.url_shortener.service.generator

interface ShortUrlGenerator {

    fun generate(originalUrl: String? = null, length: Int = 8): String
}