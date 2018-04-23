package com.stevenweathers.movies.moviecollection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit



interface MoviesService {
    @GET("movies")
    fun getMovies(): Call<MoviesResponse>
}

class MainActivity : AppCompatActivity() {
    val url = "http://movies.stevenweathers.com/api/"
    val movies: ArrayList<Movie?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit= Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
//
        val service = retrofit.create(MoviesService::class.java)

        val movieCollection = service.getMovies()

        // Creates a vertical Layout Manager
        movie_list.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        movie_list.adapter = MovieAdapter(movies, this)
    }

}