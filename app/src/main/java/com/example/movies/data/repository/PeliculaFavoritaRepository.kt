package com.example.movies.data.repository

import com.example.movies.data.local.PeliculaFavoritaDao
import com.example.movies.model.PeliculaFavorita

class PeliculaFavoritaRepository(private val peliculaFavoritaDao: PeliculaFavoritaDao) {

    suspend fun agregarPeliculaFavorita(peliculaFavorita: PeliculaFavorita) {
        peliculaFavoritaDao.agregarPeliculaFavorita(peliculaFavorita)
    }
    suspend fun eliminarPeliculaFavorita(peliculaFavorita: PeliculaFavorita) {
        peliculaFavoritaDao.eliminarPeliculaFavorita(peliculaFavorita)
    }
    suspend fun obtenerPeliculasFavoritas(): List<PeliculaFavorita> {
        return peliculaFavoritaDao.obtenerPeliculasFavoritas()
    }

}