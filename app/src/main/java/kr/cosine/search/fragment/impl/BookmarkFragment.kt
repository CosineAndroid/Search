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
import kr.cosine.search.adapter.SearchAdapter
import kr.cosine.search.databinding.FragmentBookmarkBinding
import kr.cosine.search.fragment.BaseFragment

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {

    private val searchViewModel: SearchViewModel by activityViewModels { SearchViewModelFactory() }

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.bookmarkRecyclerView.apply {
            val dividerItemDecoration = DividerItemDecoration(activity, LinearLayout.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = SearchAdapter(hideSwitch = true).apply { searchAdapter = this }
        }
        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        searchViewModel.bookmark.observe(viewLifecycleOwner) {
            searchAdapter.update(it)
        }
    }
}