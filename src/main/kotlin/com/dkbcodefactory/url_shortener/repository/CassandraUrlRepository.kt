package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url

class CassandraUrlRepository : UrlRepository {
    override fun save(url: Url) {
        TODO("Not yet implemented")
    }

    override fun findOriginalUrl(hashCode: String): Url? {
        TODO("Not yet implemented")
    }

    override fun hasCollision(hashCode: String): Boolean {
        TODO("Not yet implemented")
    }
}