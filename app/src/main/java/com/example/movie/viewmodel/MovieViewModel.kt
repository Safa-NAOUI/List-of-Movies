package com.example.movie.viewmodel

import androidx.lifecycle.*
import com.example.movie.data.local.MovieRepository
import com.example.movie.data.model.Movie
import kotlinx.coroutines.launch
/**
 * Created by Safa NAOUI on 18/02/2021.
 */
class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    // Using LiveData and caching what allMovies returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMovies: LiveData<List<Movie>> = repository.allMovies.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }
}

