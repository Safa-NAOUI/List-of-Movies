package com.example.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movie.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
public abstract class MovieRoomDatabase : RoomDatabase() {

   abstract fun movieDao(): MovieDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): MovieRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database"
                    )
                    .addCallback(MovieDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
   }

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.movieDao())
                }
            }
        }

        suspend fun populateDatabase(movieDao: MovieDao) {
            // Delete all content here.
          //  movieDao.deleteAll()

//            // Add sample words.
//            var word = Movie("Hello")
//            movieDao.insert(word)
//            word = Movie("World!")
//            movieDao.insert(word)

            // TODO: Add your own words!
        }
    }
}