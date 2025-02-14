package com.example.movies.data.remote

import com.example.movies.model.Pelicula
import com.google.gson.annotations.SerializedName

data class ResultadoPeliculas(
    @SerializedName("results") val results: List<Pelicula>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int
)
