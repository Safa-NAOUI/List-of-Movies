package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.model.Movie
import com.example.movie.ui.CellClickListener
import com.example.movie.ui.viewholder.MovieViewHolder

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
class MovieListAdapter(private val list: List<Movie>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(movie)
        }
    }

    override fun getItemCount(): Int = list.size

}
