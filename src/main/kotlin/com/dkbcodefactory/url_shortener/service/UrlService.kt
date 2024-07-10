package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url

interface UrlService {

    fun shortenUrl(originalUrl: String): Url

    fun getOriginalUrl(hashCode: String): Url?
}