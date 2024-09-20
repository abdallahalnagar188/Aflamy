package com.example.aflamy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.models.MovieModel
import com.example.domain.entity.models.MovieModelForLocal
import com.example.domain.repo.repoRoom.WishlistRepository
import com.example.domain.usecase.roomUescase.AddToWishlistUseCase
import com.example.domain.usecase.roomUescase.GetWishlistMoviesUseCase
import com.example.domain.usecase.roomUescase.RemoveFromWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val addToWishlistUseCase: AddToWishlistUseCase,
    private val removeFromWishlistUseCase: RemoveFromWishlistUseCase,
    private val getWishlistMoviesUseCase: GetWishlistMoviesUseCase,
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    private val _wishlist = MutableLiveData<List<MovieModelForLocal>>()
    val wishlist: LiveData<List<MovieModelForLocal>> get() = _wishlist

    fun addMovieToWishlist(movie: MovieModel) {
        viewModelScope.launch {
            val movieForLocal = movie.toMovieModelForLocal()
            addToWishlistUseCase(movieForLocal)
            loadWishlist()
        }
    }

    fun removeMovieFromWishlist(movie: MovieModel) {
        viewModelScope.launch {
            val movieForLocal = movie.toMovieModelForLocal()
            removeFromWishlistUseCase(movieForLocal)
            loadWishlist()
        }
    }

    fun loadWishlist() {
        viewModelScope.launch {
            _wishlist.value = getWishlistMoviesUseCase()
        }
    }

    suspend fun isMovieBookmarked(movieId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            wishlistRepository.isMovieBookmarked(movieId)
        }
    }
    private fun MovieModel.toMovieModelForLocal(): MovieModelForLocal {
        return MovieModelForLocal(
            id = id ?: 0,
            title = title ?: "",
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
        )
    }
}
