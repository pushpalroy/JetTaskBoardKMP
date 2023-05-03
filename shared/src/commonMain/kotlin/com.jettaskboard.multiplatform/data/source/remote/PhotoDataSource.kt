package com.jettaskboard.multiplatform.data.source.remote

import com.jettaskboard.multiplatform.data.model.ResultImageDataModel
import com.jettaskboard.multiplatform.data.model.SearchPhotosDataModel
import com.jettaskboard.multiplatform.data.util.NetworkResponse

interface PhotoDataSource {

    suspend fun getRandomPhotoList(collectionId: Int): NetworkResponse<ResultImageDataModel>

    suspend fun getSearchPhotoList(query: String): NetworkResponse<SearchPhotosDataModel>

}