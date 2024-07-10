package com.dkbcodefactory.url_shortener.service

import com.dkbcodefactory.url_shortener.service.generator.RandomHashCodeGenerator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RandomHashCodeGeneratorTest {

    // Subject under test
    private val sut = RandomHashCodeGenerator()

    @Test
    fun `SHOULD generate the random short url WHEN the length is set by default`(){
        // Given and When
        val result = sut.generate()
        // Then
        assertEquals(8, result.length)
    }

    @Test
    fun `SHOULD generate the random short url WHEN the length is set`(){
        // Given
        val length = 10
        // Given and When
        val result = sut.generate(length = length)
        // Then
        assertEquals(length, result.length)
    }
}