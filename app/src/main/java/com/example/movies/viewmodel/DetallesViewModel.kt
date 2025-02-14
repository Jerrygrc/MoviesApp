package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Pelicula

class DetallesViewModel: ViewModel()  {
    private val _pelicula = MutableLiveData<Pelicula>()
    val pelicula: LiveData<Pelicula> get ()= _pelicula

    fun cargarPelicula(pelicula: Pelicula){
        _pelicula.value = pelicula
    }

}