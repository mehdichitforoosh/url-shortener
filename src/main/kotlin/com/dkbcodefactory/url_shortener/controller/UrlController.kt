package com.dkbcodefactory.url_shortener.controller

import com.dkbcodefactory.url_shortener.controller.json.CreateShortUrlJson
import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.service.UrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/urls")
class UrlController @Autowired constructor(private val urlService: UrlService) {

    @PostMapping("/shorten")
    fun shortenURL(@RequestBody createShortUrlJson: CreateShortUrlJson): ResponseEntity<Url> {
        val url = urlService.shortenUrl(createShortUrlJson.originalUrl)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(url)
    }

    @GetMapping("/{shortUrl}")
    fun getOriginalUrl(@PathVariable shortUrl: String): ResponseEntity<Url?> {
        val url = urlService.getOriginalUrl(shortUrl)
        return url?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }
}