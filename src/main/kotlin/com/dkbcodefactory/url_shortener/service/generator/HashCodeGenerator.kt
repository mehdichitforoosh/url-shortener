package com.dkbcodefactory.url_shortener.service.generator

interface HashCodeGenerator {

    fun generate(originalUrl: String? = null, length: Int = 8): String
}