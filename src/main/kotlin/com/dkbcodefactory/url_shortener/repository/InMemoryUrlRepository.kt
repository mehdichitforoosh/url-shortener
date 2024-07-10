package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url

class InMemoryUrlRepository : UrlRepository {

    private val urlMap = mutableMapOf<String, Url>()

    override fun save(url: Url) {
        urlMap[url.shortUrl] = url
    }

    override fun findOriginalUrl(shortUrl: String): Url? {
        return urlMap[shortUrl]
    }

    override fun hasCollision(shortUrl: String): Boolean {
        return urlMap.containsKey(shortUrl)
    }

}