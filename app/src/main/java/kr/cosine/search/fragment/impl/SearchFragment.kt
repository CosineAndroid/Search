package kr.cosine.search.fragment.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.cosine.search.SearchViewModel
import kr.cosine.search.SearchViewModelFactory
import kr.cosine.search.databinding.FragmentSearchBinding
import kr.cosine.search.fragment.BaseFragment
import kr.cosine.search.adapter.SearchAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private companion object {
        val searchWords = listOf("kotlin", "java", "python")
    }

    private val searchViewModel: SearchViewModel by activityViewModels { SearchViewModelFactory() }

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.searchRecyclerView.apply {
            val dividerItemDecoration = DividerItemDecoration(activity, LinearLayout.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = SearchAdapter { imageDocumentEntity, isChecked ->
                if (isChecked) {
                    searchViewModel.addBookmark(imageDocumentEntity)
                } else {
                    searchViewModel.removeBookmark(imageDocumentEntity)
                }
            }.apply { searchAdapter = this }
        }
        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        searchViewModel.onSearch(searchWords.random())
        searchViewModel.serachResults.observe(viewLifecycleOwner) {
            searchAdapter.update(it)
        }
    }
}