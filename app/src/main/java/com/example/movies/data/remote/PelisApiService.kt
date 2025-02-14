package com.example.movies.data.remote

import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.GET

interface PelisApiService{
    @GET("movie/upcoming?language=es-ES")
    suspend fun getProximasPeliculas(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):Response<ResultadoPeliculas>

    @GET("movie/popular?language=es-ES")
    suspend fun getPeliculasPopulares(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):Response<ResultadoPeliculas>

    @GET("movie/top_rated?language=es-ES")
    suspend fun getPeliculasMasValoradas(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):Response<ResultadoPeliculas>

    @GET("movie/now_playing?language=es-ES")
    suspend fun getPeliculasEnCartelera(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):Response<ResultadoPeliculas>
}