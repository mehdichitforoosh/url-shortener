package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultUrlService @Autowired constructor(
    private val urlRepository: UrlRepository, private val shortUrlGenerator: ShortUrlGenerator
) : UrlService {

    private val logger = LoggerFactory.getLogger(DefaultUrlService::class.java)

    override fun shortenUrl(originalUrl: String): Url {
        logger.debug("Shorten url {}", originalUrl)
        var shortUrl = shortUrlGenerator.generate(originalUrl)
        while (urlRepository.hasCollision(shortUrl)) {
            shortUrl = shortUrlGenerator.generate(originalUrl)
        }
        val url = Url(shortUrl, originalUrl)
        urlRepository.save(url)
        return url
    }

    override fun getOriginalUrl(shortUrl: String): Url? {
        logger.debug("Get original url for {}", shortUrl)
        return urlRepository.findOriginalUrl(shortUrl)
    }

}