package com.jettaskboard.multiplatform.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrlDataModel(
    @SerialName("raw")
    val raw: String? = null,
    @SerialName("full")
    val full: String? = null,
    @SerialName("regular")
    val regular: String? = null
)