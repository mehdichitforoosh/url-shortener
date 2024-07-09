package com.dkbcodefactory.url_shortener.controller

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.service.UrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/urls")
class UrlController @Autowired constructor(private val urlService: UrlService) {

    @PostMapping("/shorten")
    fun shortenURL(@RequestBody originalUrl: String): Url {
        return urlService.shortenUrl(originalUrl)
    }

    @GetMapping("/{shortUrl}")
    fun getOriginalUrl(@PathVariable shortUrl: String): Url? {
        return urlService.getOriginalUrl(shortUrl)
    }
}