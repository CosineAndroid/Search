package kr.cosine.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kr.cosine.search.infrastructure.persistence.repository.SearchRepositoryImpl
import kr.cosine.search.network.RetrofitClient

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val searchRepository by lazy { SearchRepositoryImpl(RetrofitClient.search) }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SearchViewModel(searchRepository) as T
    }
}