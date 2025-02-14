package com.example.movies.data.repository

import com.example.movies.data.remote.PelisApiService
import com.example.movies.model.Pelicula

class PeliculaRepository(private val apiService: PelisApiService) {

    suspend fun obtenerProximasPeliculas(apiKey: String, page: Int): List<Pelicula>? {
        val response = apiService.getProximasPeliculas(apiKey, page)
        return if(response.isSuccessful) response.body()?.results else null
    }
    suspend fun obtenerPeliculasPopulares(apiKey: String, page: Int): List<Pelicula>? {
        val response = apiService.getPeliculasPopulares(apiKey, page)
        return if(response.isSuccessful) response.body()?.results else null
    }
    suspend fun obtenerPeliculasMasValoradas(apiKey: String, page: Int): List<Pelicula>? {
        val response = apiService.getPeliculasMasValoradas(apiKey, page)
        return if(response.isSuccessful) response.body()?.results else null
    }
    suspend fun obtenerPeliculasEnCartelera(apiKey: String, page: Int): List<Pelicula>? {
        val response = apiService.getPeliculasEnCartelera(apiKey, page)
        return if(response.isSuccessful) response.body()?.results else null
    }

}