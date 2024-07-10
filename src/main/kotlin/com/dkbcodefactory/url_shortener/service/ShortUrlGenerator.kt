package com.dkbcodefactory.url_shortener.service

interface ShortUrlGenerator {

    fun generate(originalUrl: String? = null, length: Int = 8): String
}