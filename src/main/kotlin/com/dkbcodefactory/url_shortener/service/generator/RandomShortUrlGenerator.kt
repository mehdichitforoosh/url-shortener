package com.dkbcodefactory.url_shortener.service.generator

class RandomShortUrlGenerator : ShortUrlGenerator {

    override fun generate(originalUrl: String?, length: Int): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { characters.random() }
            .joinToString("")
    }
}