package com.jettaskboard.multiplatform.data.repo

import com.jettaskboard.multiplatform.data.model.toDomain
import com.jettaskboard.multiplatform.data.source.remote.PhotoDataSource
import com.jettaskboard.multiplatform.data.util.NetworkResponse
import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel
import com.jettaskboard.multiplatform.domain.repo.PhotoRepo

class PhotoRepoImpl(
    private val photoDataSource: PhotoDataSource
) : PhotoRepo {

    override suspend fun getRandomPhotoList(collectionId: Int): NetworkResponse<List<ChangeBackgroundPhotoModel>?> {
        return when (val result = photoDataSource.getRandomPhotoList(collectionId)) {
            is NetworkResponse.Success -> NetworkResponse.Success(
                data = result.data.map { responseModel ->
                    responseModel.toDomain()
                }
            )

            is NetworkResponse.Failure -> NetworkResponse.Failure(Exception(result.throwable))
        }
    }

    override suspend fun getSearchResultPhotoList(query: String): NetworkResponse<List<ChangeBackgroundPhotoModel>?> {
        return when (val result = photoDataSource.getSearchPhotoList(query = query)) {
            is NetworkResponse.Success -> NetworkResponse.Success(
                data = result.data.resultImageDataModels?.map { responseModel ->
                    responseModel.toDomain()
                }
            )

            is NetworkResponse.Failure -> NetworkResponse.Failure(Exception(result.throwable))
        }
    }
}