package com.example.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.model.PeliculaFavorita
import com.example.movies.model.Usuario

@Database(entities = [Usuario::class, PeliculaFavorita::class], version = 2)
abstract class MyDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun peliculaFavoritaDao(): PeliculaFavoritaDao

}