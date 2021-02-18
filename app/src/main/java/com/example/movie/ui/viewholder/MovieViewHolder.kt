package com.example.movie.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.Movie

/**
 * Created by Safa NAOUI on 18/02/2021.
 */
class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list_movie, parent, false)) {
    private var mTitleView: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.txt_title)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.movie
    }

}