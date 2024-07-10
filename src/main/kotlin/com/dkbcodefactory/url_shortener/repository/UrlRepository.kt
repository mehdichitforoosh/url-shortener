package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url

interface UrlRepository {

    fun save(url: Url)

    fun findOriginalUrl(hashCode: String): Url?

    fun hasCollision(hashCode: String): Boolean
}