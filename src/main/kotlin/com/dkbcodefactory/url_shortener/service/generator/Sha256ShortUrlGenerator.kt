package com.dkbcodefactory.url_shortener.service.generator

import java.security.MessageDigest
import java.util.*

class Sha256ShortUrlGenerator : ShortUrlGenerator {

    override fun generate(originalUrl: String?, length: Int): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(originalUrl!!.toByteArray())
        val shortUrl = Base64.getUrlEncoder()
            .encodeToString(bytes)
            .substring(0, length)
        return shortUrl
    }
}