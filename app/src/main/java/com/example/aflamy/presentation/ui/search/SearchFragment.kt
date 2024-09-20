package com.example.aflamy.presentation.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentSearchBinding
import com.example.aflamy.presentation.adapter.search.SearchPagingAdapter
import com.example.aflamy.presentation.ui.BindingFragment
import com.example.aflamy.presentation.viewmodel.SearchViewModel
import com.example.domain.entity.models.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import com.example.aflamy.genrel.navOptionsAnimation

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(),
    SearchPagingAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSearchBinding::inflate

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var adapter: SearchPagingAdapter

    private val searchHandler = Handler(Looper.getMainLooper())
    private var searchRunnable: Runnable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        observeSearchResults()
    }

    private fun setupListeners() {
        binding.apply {
            // Listen for changes in the search EditText
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    searchRunnable?.let { searchHandler.removeCallbacks(it) }

                    // Debounce the search input to avoid searching on every keystroke
                    searchRunnable = Runnable {
                        val query = s.toString().trim()
                        if (query.isNotEmpty()) {
                            fetchPopularMovies(query)
                        }
                    }

                    searchRunnable?.let {
                        searchHandler.postDelayed(it, 500) // 500ms delay before triggering search
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            searchButton.setOnClickListener {
                val query = searchEditText.text.toString().trim()
                if (query.isNotEmpty()) {
                    fetchPopularMovies(query)
                }else{
                    searchEditText.error = "Please enter a movie name"
                }
                hideKeyboard()
            }
        }
    }

    private fun setupRecyclerView() {
        adapter.setListener(this)
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.setHasFixedSize(true)
    }

    private fun fetchPopularMovies(query: String) {
        lifecycleScope.launch {
            viewModel.getSearchResult(
                apiKey = API_Key,
                query = query
            ).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun observeSearchResults() {
        lifecycleScope.launch {
            viewModel.getSearchResult(API_Key,"")?.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onItemClicked(model: MovieModel) {
        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        },navOptionsAnimation())
    }
}


