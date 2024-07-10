package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url

class InMemoryUrlRepository : UrlRepository {

    private val urlMap = mutableMapOf<String, Url>()

    override fun save(url: Url) {
        urlMap[url.hashCode] = url
    }

    override fun findOriginalUrl(hashCode: String): Url? {
        return urlMap[hashCode]
    }

    override fun hasCollision(hashCode: String): Boolean {
        return urlMap.containsKey(hashCode)
    }

}