package com.example.aflamy.presentation.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.databinding.FragmentFavoriteBinding
import com.example.aflamy.genrel.navOptionsAnimation
import com.example.aflamy.presentation.adapter.wishlist.WishListAdapter
import com.example.aflamy.presentation.ui.BindingFragment
import com.example.aflamy.presentation.viewmodel.WishlistViewModel
import com.example.domain.entity.models.MovieModelForLocal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BindingFragment<FragmentFavoriteBinding>(),
    WishListAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentFavoriteBinding::inflate

    private val viewModel: WishlistViewModel by viewModels()

    // Initialize adapter
    private lateinit var adapter: WishListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter
        adapter = WishListAdapter()

        // Setup RecyclerView and observe ViewModel
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvFavorite.adapter = adapter
        adapter.setListener(this)
    }

    private fun observeViewModel() {
        viewModel.loadWishlist()
        viewModel.wishlist.observe(viewLifecycleOwner) { movies ->
            // Assuming you want to use MovieModelForLocal directly
            adapter.submitList(movies)
        }
    }

    override fun onItemClicked(model: MovieModelForLocal) {
        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }, navOptionsAnimation())
    }
}
