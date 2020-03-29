package com.example.pagination.data.api

import com.example.pagination.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

     @GET("movie/popular")
     fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

}