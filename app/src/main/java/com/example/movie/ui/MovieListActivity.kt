package com.example.movie.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.*
import com.example.movie.data.model.Movie
import com.example.movie.ui.adapter.MovieListAdapter
import com.example.movie.viewmodel.MovieViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
class MovieListActivity : AppCompatActivity(), CellClickListener {
    private val newMovieActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val txt_not_found = findViewById<TextView>(R.id.txt_not_found_data)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedMovies.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        movieViewModel.allMovies.observe(this) { movies ->
            movies.let {
                if (movies.isEmpty()) {

                    txt_not_found.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {

                    txt_not_found.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    val adapter = MovieListAdapter(movies, this)
                    recyclerView.adapter = adapter
                }
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MovieListActivity, NewMovieActivity::class.java)
            startActivityForResult(intent, newMovieActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newMovieActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewMovieActivity.EXTRA_REPLY)?.let { reply ->
                val movie = Movie(0, reply)
                movieViewModel.insert(movie)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((application as MovieApplication).repository)
    }


    override fun onCellClickListener(movie: Movie) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                movieViewModel.deleteMovie(movie)

            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

    }

}