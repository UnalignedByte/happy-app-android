package com.unalignedbyte.happyapp.data

import java.net.URL

interface WebApiProtocol {
    val happinessStatusUrl: URL
}

class WebApi: WebApiProtocol {
    override val happinessStatusUrl = URL("https", "unalignedbyte.com", "api/happiness")
}