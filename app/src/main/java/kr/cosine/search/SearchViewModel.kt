package kr.cosine.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.cosine.search.domain.repository.SearchRepository
import kr.cosine.search.infrastructure.persistence.entity.ImageDocumentEntity

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchResults: MutableLiveData<List<ImageDocumentEntity>> = MutableLiveData()
    val serachResults: LiveData<List<ImageDocumentEntity>> get() = _searchResults

    fun onSearch(query: String) {
        viewModelScope.launch {
            val searchImageEntity = searchRepository.getSearchImage(query)
            _searchResults.value = searchImageEntity.documents
        }
    }
}