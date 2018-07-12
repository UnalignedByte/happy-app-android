package com.unalignedbyte.happyapp

import org.junit.Test
import org.junit.Assert.*
import com.unalignedbyte.happyapp.data.WebApi

class WebApiTests {
    @Test
    fun testStatusUrl() {
        val webApi = WebApi()
        assertEquals(webApi.happinessStatusUrl.host, "unalignedbyte.com")
        assertFalse(webApi.happinessStatusUrl.path.isEmpty())
    }

    @Test fun testSubmissionUrl() {
        val webApi = WebApi()
        assertEquals(webApi.happinessStatusUrl.host, "unalignedbyte.com")
        assertFalse(webApi.happinessSubmissionUrl.path.isEmpty())
    }
}