package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import org.springframework.stereotype.Service

@Service
class DefaultUrlService : UrlService {
    override fun shortenUrl(originalUrl: String): Url {
        TODO("Not yet implemented")
    }

    override fun getOriginalUrl(shortUrl: String): Url? {
        TODO("Not yet implemented")
    }
}