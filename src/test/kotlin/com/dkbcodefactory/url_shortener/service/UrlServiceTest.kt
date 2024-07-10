package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.repository.InMemoryUrlRepository
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UrlServiceTest {

    private lateinit var urlRepository: UrlRepository
    private lateinit var shortUrlGenerator: ShortUrlGenerator

    private val shortUrl = "aBc43GHr"
    private val originalUrl = "https://www.example.com"

    // Subject under test
    private lateinit var sut: UrlService

    @BeforeEach
    fun setup() {
        this.urlRepository = spyk(InMemoryUrlRepository())
        this.shortUrlGenerator = mockk()
        this.sut = DefaultUrlService(this.urlRepository, this.shortUrlGenerator)
    }

    @Test
    fun `SHOULD return the short url`() {
        // Given
        every { shortUrlGenerator.generate(originalUrl) } returns shortUrl
        // When
        val result = sut.shortenUrl(originalUrl)
        // Then
        verify(exactly = 1) { urlRepository.hasCollision(shortUrl) }
        verify(exactly = 1) { urlRepository.save(any()) }
        assertEquals(shortUrl, result.shortUrl)
    }

    @Test
    fun `SHOULD return the short url with checking collisions`() {
        // Given
        val newShortUrl = "bcDF653w"
        val values = listOf(shortUrl, newShortUrl)
        var count = 0
        every { shortUrlGenerator.generate(originalUrl) } answers { values[count++] }
        urlRepository.save(Url(shortUrl, originalUrl))
        // When
        val result = sut.shortenUrl(originalUrl)
        // Then
        verify(exactly = 2) { urlRepository.hasCollision(any()) }
        verify(exactly = 2) { urlRepository.save(any()) }
        assertEquals(newShortUrl, result.shortUrl)
    }

    @Test
    fun `SHOULD return the original url`() {
        // Given
        every { shortUrlGenerator.generate(originalUrl) } returns shortUrl
        sut.shortenUrl(originalUrl)
        // When
        val result = sut.getOriginalUrl(shortUrl)
        // Then
        assertEquals(originalUrl, result!!.originalUrl)
    }

    @Test
    fun `SHOULD return null WHEN there is no shortUrl `() {
        // Given and When
        val result = sut.getOriginalUrl(shortUrl)
        // Then
        assertNull(result)
    }
}