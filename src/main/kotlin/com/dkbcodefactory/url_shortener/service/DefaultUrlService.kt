package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import com.dkbcodefactory.url_shortener.service.generator.HashCodeGenerator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DefaultUrlService @Autowired constructor(
    private val urlRepository: UrlRepository, private val hashCodeGenerator: HashCodeGenerator
) : UrlService {

    private val logger = LoggerFactory.getLogger(DefaultUrlService::class.java)

    @Value("\${hash.length: 8}")
    private var length: Int = 8

    override fun shortenUrl(originalUrl: String): Url {
        logger.debug("Shorten url {}", originalUrl)
        var hashCode = hashCodeGenerator.generate(originalUrl, length)
        while (urlRepository.hasCollision(hashCode)) {
            // Add a unique id to the original url to make it unique to solve the collision issue in sha-256 generator
            val uuid = UUID.randomUUID().toString()
            val newUrlToHash = "$originalUrl+$uuid"
            hashCode = hashCodeGenerator.generate(newUrlToHash)
        }
        val url = Url(hashCode, originalUrl)
        urlRepository.save(url)
        return url
    }

    override fun getOriginalUrl(hashCode: String): Url? {
        logger.debug("Get original url for {}", hashCode)
        return urlRepository.findOriginalUrl(hashCode)
    }

}