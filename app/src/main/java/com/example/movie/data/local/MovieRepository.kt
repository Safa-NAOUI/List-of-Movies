package com.example.movie.data.local

import androidx.annotation.WorkerThread
import com.example.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MovieRepository(private val movieDao: MovieDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMovies: Flow<List<Movie>> = movieDao.getAlphabetizedMovie()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

}