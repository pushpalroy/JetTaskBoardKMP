package com.jettaskboard.multiplatform.domain.repo

import com.jettaskboard.multiplatform.data.util.NetworkResponse
import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel

interface PhotoRepo {
    suspend fun getRandomPhotoList(collectionId: Int): NetworkResponse<ChangeBackgroundPhotoModel?>
    suspend fun getSearchResultPhotoList(query: String): NetworkResponse<List<ChangeBackgroundPhotoModel>?>
}