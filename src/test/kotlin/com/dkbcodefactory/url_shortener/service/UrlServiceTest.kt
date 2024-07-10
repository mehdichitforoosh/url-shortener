package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.repository.InMemoryUrlRepository
import com.dkbcodefactory.url_shortener.repository.UrlRepository
import com.dkbcodefactory.url_shortener.service.generator.HashCodeGenerator
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
    private lateinit var hashCodeGenerator: HashCodeGenerator

    private val hashCode = "aBc43GHr"
    private val originalUrl = "https://www.example.com"

    // Subject under test
    private lateinit var sut: UrlService

    @BeforeEach
    fun setup() {
        this.urlRepository = spyk(InMemoryUrlRepository())
        this.hashCodeGenerator = mockk()
        this.sut = DefaultUrlService(this.urlRepository, this.hashCodeGenerator)
    }

    @Test
    fun `SHOULD return the short url`() {
        // Given
        every { hashCodeGenerator.generate(originalUrl) } returns hashCode
        // When
        val result = sut.shortenUrl(originalUrl)
        // Then
        verify(exactly = 1) { urlRepository.hasCollision(hashCode) }
        verify(exactly = 1) { urlRepository.save(any()) }
        assertEquals(hashCode, result.hashCode)
    }

    @Test
    fun `SHOULD return the short url with checking collisions`() {
        // Given
        val newHashCode = "bcDF653w"
        val values = listOf(hashCode, newHashCode)
        var count = 0
        every { hashCodeGenerator.generate(originalUrl) } answers { values[count++] }
        urlRepository.save(Url(hashCode, originalUrl))
        // When
        val result = sut.shortenUrl(originalUrl)
        // Then
        verify(exactly = 2) { urlRepository.hasCollision(any()) }
        verify(exactly = 2) { urlRepository.save(any()) }
        assertEquals(newHashCode, result.hashCode)
    }

    @Test
    fun `SHOULD return the original url`() {
        // Given
        every { hashCodeGenerator.generate(originalUrl) } returns hashCode
        sut.shortenUrl(originalUrl)
        // When
        val result = sut.getOriginalUrl(hashCode)
        // Then
        assertEquals(originalUrl, result!!.originalUrl)
    }

    @Test
    fun `SHOULD return null WHEN there is no shortUrl `() {
        // Given and When
        val result = sut.getOriginalUrl(hashCode)
        // Then
        assertNull(result)
    }
}