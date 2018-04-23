package com.stevenweathers.movies.moviecollection

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter(private val items: List<Movie?>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        holder?.movieTitle?.text = movie?.title
        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/${movie?.tmdb_image_url}").into(holder?.moviePoster)


    }

    // Gets the number of movies in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each movie to
    val movieTitle = view.movie_title!!
    val moviePoster = view.movie_poster!!
}