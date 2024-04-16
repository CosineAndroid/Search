package kr.cosine.search.infrastructure.persistence.repository

import kr.cosine.search.domain.remote.SearchRemoteDatasource
import kr.cosine.search.domain.repository.SearchRepository
import kr.cosine.search.infrastructure.persistence.entity.SearchImageEntity
import kr.cosine.search.infrastructure.persistence.entity.toEntity

class SearchRepositoryImpl(
    private val remoteDatasource: SearchRemoteDatasource
) : SearchRepository {

    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchImageEntity {
        return remoteDatasource.getSearchImage(query, sort, page, size).toEntity()
    }
}