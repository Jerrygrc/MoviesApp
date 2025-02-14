package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.model.PeliculaFavorita

@Dao
interface PeliculaFavoritaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPeliculaFavorita(peliculaFavorita: PeliculaFavorita)

    @Delete
    suspend fun eliminarPeliculaFavorita(peliculaFavorita: PeliculaFavorita)

    @Query("SELECT * FROM peliculas_favoritas")
    suspend fun obtenerPeliculasFavoritas(): List<PeliculaFavorita>
}