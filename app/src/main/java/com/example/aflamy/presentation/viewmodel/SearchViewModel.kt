package com.example.aflamy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.models.MovieModel
import com.example.domain.state.UiState
import com.example.domain.usecase.remoteUseCase.GetSearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: GetSearchResult
) : ViewModel() {

    suspend fun getSearchResult(apiKey: String, query: String): Flow<PagingData<MovieModel>> {
        return searchUseCase(
            query = query,
            apiKey = apiKey
        ).cachedIn(viewModelScope)
    }
}