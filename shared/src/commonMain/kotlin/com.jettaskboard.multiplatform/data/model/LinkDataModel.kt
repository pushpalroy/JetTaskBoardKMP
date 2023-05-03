package com.jettaskboard.multiplatform.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkDataModel(
    @SerialName("self")
    val self: String? = null,
    @SerialName("html")
    val html: String? = null,
    @SerialName("download")
    val download: String? = null,
    @SerialName("download_location")
    val downloadLocation: String? = null
)