package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultUrlService @Autowired constructor(
    private val urlRepository: UrlRepository, private val shortUrlGenerator: ShortUrlGenerator
) : UrlService {

    override fun shortenUrl(originalUrl: String): Url {
        var shortUrl = shortUrlGenerator.generate(originalUrl)
        while (urlRepository.hasCollision(shortUrl)) {
            shortUrl = shortUrlGenerator.generate(originalUrl)
        }
        val url = Url(shortUrl, originalUrl)
        urlRepository.save(url)
        return url
    }

    override fun getOriginalUrl(shortUrl: String): Url? {
        return urlRepository.findOriginalUrl(shortUrl)
    }

}