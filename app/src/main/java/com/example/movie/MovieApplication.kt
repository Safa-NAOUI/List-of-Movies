package com.example.movie

import android.app.Application
import com.example.movie.data.local.MovieRepository
import com.example.movie.data.local.MovieRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
class MovieApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MovieRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MovieRepository(database.movieDao()) }
}