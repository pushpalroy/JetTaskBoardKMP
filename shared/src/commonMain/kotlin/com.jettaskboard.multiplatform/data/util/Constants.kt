package com.jettaskboard.multiplatform.data.util

object Constants {
    const val BASE_URL = "https://api.unsplash.com/"
    const val PHOTOS_RANDOM_ENDPOINT = "photos/random"
    const val PHOTOS_SEARCH_ENDPOINT = "search/photos"

    const val PARAM_AUTH_CLIENT_ID = "client_id"
    const val PARAM_QUERY_PER_PAGE = "per_page"
    const val PARAM_QUERY = "query"
    const val PARAM_QUERY_COUNT = "count"
    const val PARAM_QUERY_COLLECTION = "collection"

    const val DEFAULT_BOARD_BG = "https://images.unsplash.com/photo-1523895665936-7bfe172b757d"
}

object UnsplashCollection {
    const val RANDOM_NATURE_COLLECTION_ID = 1319040
    const val RANDOM_COLORS_COLLECTION_ID = 1814606
}