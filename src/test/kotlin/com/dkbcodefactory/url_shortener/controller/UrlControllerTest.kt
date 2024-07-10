package com.dkbcodefactory.url_shortener.controller

import com.dkbcodefactory.url_shortener.controller.json.CreateShortUrlJson
import com.dkbcodefactory.url_shortener.model.Url
import com.dkbcodefactory.url_shortener.service.UrlService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class UrlControllerTest {

    private lateinit var mockMvc: MockMvc
    private lateinit var urlService: UrlService
    private val objectMapper: ObjectMapper = ObjectMapper()

    private val shortUrl = "aBc43GHr"
    private val originalUrl = "https://www.example.com"

    // Subject under test
    private lateinit var urlController: UrlController

    @BeforeEach
    fun setup() {
        this.urlService = mockk()
        this.urlController = UrlController(this.urlService)
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.urlController).build()
    }

    @Test
    fun `SHOULD return the short url`() {
        // Given
        val createShortUrlJson = CreateShortUrlJson(originalUrl)
        every { urlService.shortenUrl(originalUrl) } returns Url(shortUrl, originalUrl)
        // When and Then
        mockMvc.perform(
            post("/api/v1/urls/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createShortUrlJson))
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun `SHOULD return the original url`() {
        // Given
        every { urlService.getOriginalUrl(shortUrl) } returns Url(shortUrl, originalUrl)
        // When and Then
        mockMvc.perform(
            get("/api/v1/urls/{shortUrl}", shortUrl)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.originalUrl").value(originalUrl))
    }

    @Test
    fun `SHOULD return not found status code WHEN the short url is not found`() {
        // Given
        every { urlService.getOriginalUrl(shortUrl) } returns null
        // When and Then
        mockMvc.perform(
            get("/api/v1/urls/{shortUrl}", shortUrl)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
    }
}