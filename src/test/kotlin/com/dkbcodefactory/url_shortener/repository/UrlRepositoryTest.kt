package com.dkbcodefactory.url_shortener.repository

import com.dkbcodefactory.url_shortener.model.Url
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class UrlRepositoryTest {

    private val firstUrl = Url("Ab2EcfG0", "https://www.google.com")
    private val secondUrl = Url("Bb2EcfG1", "https://www.yahoo.com")

    // Subject under test
    private lateinit var sut: UrlRepository

    @BeforeEach
    fun setup() {
        this.sut = InMemoryUrlRepository()
    }

    @Test
    fun `SHOULD save the url`() {
        // Given and When
        sut.save(firstUrl)
        // Then
        assertEquals(firstUrl, sut.findOriginalUrl(firstUrl.shortUrl))
    }

    @Test
    fun `SHOULD return the original url WHEN the short url has already been added`() {
        // Given
        sut.save(firstUrl)
        sut.save(secondUrl)
        // When
        val result = sut.findOriginalUrl(firstUrl.shortUrl)
        // Then
        assertEquals(firstUrl, result)
    }

    @Test
    fun `SHOULD return true calling has collision method WHEN the short url has already been added`() {
        // Given
        sut.save(firstUrl)
        sut.save(secondUrl)
        // When
        val result = sut.hasCollision(firstUrl.shortUrl)
        // Then
        assertTrue { result }
    }

    @Test
    fun `SHOULD return false calling has collision method WHEN the short url has not already been added`() {
        // Given
        sut.save(firstUrl)
        sut.save(secondUrl)
        // When
        val result = sut.hasCollision("Cb2EcfG2")
        // Then
        assertFalse { result }
    }
}