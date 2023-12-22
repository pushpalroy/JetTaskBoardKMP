package com.jettaskboard.multiplatform.domain.usecase

import com.jettaskboard.multiplatform.data.util.NetworkResponse
import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel
import com.jettaskboard.multiplatform.domain.repo.PhotoRepo
import com.jettaskboard.multiplatform.util.Result

class GetRandomPhotoListUseCase(
    private val photoRepo: PhotoRepo
) : UseCase<Result<List<ChangeBackgroundPhotoModel>>, Int> {
    suspend operator fun invoke(collectionId: Int) =
        when (val result = photoRepo.getRandomPhotoList(collectionId)) {
            is NetworkResponse.Success -> Result.Success(result.data)
            is NetworkResponse.Failure -> Result.Error(Exception(result.throwable))
        }
}