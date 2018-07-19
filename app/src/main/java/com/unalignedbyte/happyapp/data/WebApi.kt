package com.unalignedbyte.happyapp.data

import java.net.URL

interface WebApiProtocol {
    val happinessStatusUrl: URL
    val happinessSubmissionUrl: URL
}

class WebApi: WebApiProtocol {
    override val happinessStatusUrl = URL("https", "unalignedbyte.com", "api/happiness")
    override val happinessSubmissionUrl = URL("https", "unalignedbyte.com", "api/happiness")
}