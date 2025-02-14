package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.PeliculaRepository
import com.example.movies.model.Pelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculaViewModel(private val repository: PeliculaRepository) : ViewModel() {
    private val _peliculas = MutableLiveData<List<Pelicula>?>()
    val peliculas: MutableLiveData<List<Pelicula>?> get() = _peliculas

    val apiKey = ""

    fun cargarPeliculasMasValoradas(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val peliculas = repository.obtenerPeliculasMasValoradas(apiKey, page)
            if(peliculas != null){
                withContext(Dispatchers.Main) {
                    _peliculas.value = peliculas
                }
            }
        }
    }

    fun cargarPeliculasPopulares(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val peliculas = repository.obtenerPeliculasPopulares(apiKey, page)
            if(peliculas != null){
                withContext(Dispatchers.Main) {
                    _peliculas.value = peliculas
                }
            }
        }
    }

    fun cargarPeliculasEnCartelera(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val peliculas = repository.obtenerPeliculasEnCartelera(apiKey, page)
            if(peliculas != null){
                withContext(Dispatchers.Main) {
                    _peliculas.value = peliculas
                }
            }
        }
    }

    fun cargarProximasPeliculas(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val peliculas = repository.obtenerProximasPeliculas(apiKey, page)
            if (peliculas != null) {
                withContext(Dispatchers.Main) {
                    _peliculas.value = peliculas
                }
            }
        }
    }
}