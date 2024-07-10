package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.service.generator.Sha256HashCodeGenerator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Sha256HashCodeGeneratorTest {

    // Subject under test
    private val sut = Sha256HashCodeGenerator()

    @Test
    fun `SHOULD generate the short url WHEN the length is set by default`() {
        // Given
        val originalUrl = "https://www.google.com"
        //  When
        val result = sut.generate(originalUrl)
        // Then
        assertEquals(8, result.length)
    }

    @Test
    fun `SHOULD generate the short url WHEN the length is set`() {
        // Given
        val length = 10
        val originalUrl = "https://www.google.com"
        // Given and When
        val result = sut.generate(originalUrl, length)
        // Then
        assertEquals(length, result.length)
    }

    @Test
    fun `SHOULD generate the same short url WHEN the original url is the same`() {
        // Given
        val hashCode = "rGu2aeQO"
        val originalUrl = "https://www.google.com"
        //  When
        val result = sut.generate(originalUrl)
        // Then
        assertEquals(hashCode, result)
    }
}