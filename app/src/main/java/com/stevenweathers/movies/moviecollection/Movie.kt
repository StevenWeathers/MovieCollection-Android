package com.stevenweathers.movies.moviecollection

import com.squareup.moshi.Json

data class Movie(
    val _id: String,
    val title: String,
    val slug: String,
    val year: String,
    val format: String,
    val tmdb_id: String,
    val tmdb_image_url: String,
    val upc: String?
)

data class Movies(
    val movies: List<Movie>
)

class MoviesResponse (
    val data: Movies
)