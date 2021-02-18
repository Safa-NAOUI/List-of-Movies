package com.example.movie.data.local

import androidx.room.*
import com.example.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY movie ASC")
    fun getAlphabetizedMovie(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteMovie(movie: Movie?)
}