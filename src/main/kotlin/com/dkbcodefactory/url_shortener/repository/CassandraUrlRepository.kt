package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url

class CassandraUrlRepository: UrlRepository {
    override fun save(url: Url) {
        TODO("Not yet implemented")
    }

    override fun findOriginalUrl(shortUrl: String): Url? {
        TODO("Not yet implemented")
    }

    override fun hasCollision(shortUrl: String): Boolean {
        TODO("Not yet implemented")
    }
}