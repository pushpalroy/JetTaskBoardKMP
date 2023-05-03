package com.jettaskboard.multiplatform.data.source.remote

import com.jettaskboard.multiplatform.data.model.ResultImageDataModel
import com.jettaskboard.multiplatform.data.model.SearchPhotosDataModel
import com.jettaskboard.multiplatform.data.util.Constants
import com.jettaskboard.multiplatform.data.util.NetworkResponse
import com.jettaskboard.multiplatform.data.util.safeApiCallHandler
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class PhotoDataSourceImpl(
    private val client: HttpClient
) : PhotoDataSource {

    override suspend fun getRandomPhotoList(collectionId: Int): NetworkResponse<List<ResultImageDataModel>> {
        return safeApiCallHandler {
            client
                .get {
                    url {
                        url(Constants.PHOTOS_RANDOM_ENDPOINT)
                        parameters.append(Constants.PARAM_QUERY_COLLECTION, collectionId.toString())
                        parameters.append(Constants.PARAM_QUERY_COUNT, "8")
                    }
                }
        }
    }

    override suspend fun getSearchPhotoList(query: String): NetworkResponse<SearchPhotosDataModel> {
        return safeApiCallHandler {
            client
                .get {
                    url {
                        url(Constants.PHOTOS_SEARCH_ENDPOINT)
                        parameters.append(Constants.PARAM_QUERY, query)
                        parameters.append(Constants.PARAM_QUERY_PER_PAGE, "8")
                    }
                }
        }
    }
}