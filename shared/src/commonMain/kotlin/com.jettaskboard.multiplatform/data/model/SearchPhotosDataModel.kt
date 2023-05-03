package com.jettaskboard.multiplatform.data.model

import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPhotosDataModel(
    @SerialName("total")
    val total: Int? = 0,
    @SerialName("total_pages")
    val totalPages: Int? = 0,
    @SerialName("results")
    val resultImageDataModels: List<ResultImageDataModel>? = listOf()
)

@Serializable
data class ResultImageDataModel(
    @SerialName("id")
    val id: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("urls")
    val urls: UrlDataModel? = UrlDataModel(),
    @SerialName("links")
    val links: LinkDataModel? = LinkDataModel(),
)

fun ResultImageDataModel.toDomain(): ChangeBackgroundPhotoModel {
    return ChangeBackgroundPhotoModel(
        imageUrl = urls?.regular,
        imageName = description,
    )
}