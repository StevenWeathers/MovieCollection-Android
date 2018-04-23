package com.stevenweathers.movies.moviecollection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import android.os.StrictMode
import android.support.v7.widget.DividerItemDecoration




interface MoviesService {
    @GET("movies")
    fun getMovies(): Call<MoviesResponse>
}

class MainActivity : AppCompatActivity() {
    private val url = "http://movies.stevenweathers.com/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val retrofit= Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val service = retrofit.create(MoviesService::class.java)
        val getMovieCollection = service.getMovies().execute().body()

        // @TODO - convert to async call to avoid above policy stuff
//        call.enqueue(object : Callback<MyItem>() {
//            fun onResponse(call: Call<MyItem>, response: Response<MyItem>) {
//                val myItem = response.body()
//            }
//
//            fun onFailure(call: Call<MyItem>, t: Throwable) {
//                //Handle failure
//            }
//        })

        // Creates a vertical Layout Manager
        movie_list.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        movie_list.adapter = MovieAdapter(getMovieCollection?.data!!.movies, this)
    }

}