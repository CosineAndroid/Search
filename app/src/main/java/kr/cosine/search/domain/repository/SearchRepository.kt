package kr.cosine.search.domain.repository

import kr.cosine.search.infrastructure.persistence.entity.SearchImageEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchImageEntity
}