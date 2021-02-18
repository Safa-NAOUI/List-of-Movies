package com.example.movie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
@Entity(tableName = "movie_table")
class Movie(
    @PrimaryKey(autoGenerate = true) var movieId: Int = 0,
    @ColumnInfo(name = "movie") val movie: String
)