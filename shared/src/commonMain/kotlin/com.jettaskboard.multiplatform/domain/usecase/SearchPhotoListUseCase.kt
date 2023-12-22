package com.jettaskboard.multiplatform.domain.usecase

import com.jettaskboard.multiplatform.data.util.NetworkResponse
import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel
import com.jettaskboard.multiplatform.domain.repo.PhotoRepo
import com.jettaskboard.multiplatform.util.Result

class SearchPhotoListUseCase(
    private val photoRepo: PhotoRepo
) : UseCase<ChangeBackgroundPhotoModel, String> {

    suspend operator fun invoke(query: String) =
        when (val result = photoRepo.getSearchResultPhotoList(query)) {
            is NetworkResponse.Success -> Result.Success(result.data)
            is NetworkResponse.Failure -> Result.Error(Exception(result.throwable))
        }
}