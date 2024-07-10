package com.dkbcodefactory.url_shortener.controller.json

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateShortUrlJson(@JsonProperty("originalUrl") val originalUrl: String)