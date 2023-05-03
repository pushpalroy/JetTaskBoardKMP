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
    val regular: String? = null,
    @SerialName("small")
    val small: String? = null,
    @SerialName("thumb")
    val thumb: String? = null,
    @SerialName("small_s3")
    val smallS3: String? = null
)