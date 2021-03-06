package com.unalignedbyte.happyapp.data.entities

import kotlinx.serialization.*

@Serializable
data class HappinessStatus(val overallPercentage: Int, val submissionsCount: Int)