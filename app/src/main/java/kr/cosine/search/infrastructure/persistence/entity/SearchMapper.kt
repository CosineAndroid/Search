package kr.cosine.search.infrastructure.persistence.entity

import kr.cosine.search.domain.model.ImageDocumentResponse
import kr.cosine.search.domain.model.MetaResponse
import kr.cosine.search.domain.model.SearchImageResponse

fun SearchImageResponse.toEntity(): SearchImageEntity {
    return SearchImageEntity(meta?.toEntity(), documents?.map(ImageDocumentResponse::toEntity))
}

fun MetaResponse.toEntity(): MetaEntity {
    return MetaEntity(totalCount, pageableCount, isEnd)
}

fun ImageDocumentResponse.toEntity(): ImageDocumentEntity {
    return ImageDocumentEntity(
        collection,
        thumbnailUrl,
        imageUrl,
        width,
        height,
        displaySitename,
        docUrl,
        datetime,
    )
}

