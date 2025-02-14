package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.MyApplication
import com.example.movies.data.repository.PeliculaFavoritaRepository
import com.example.movies.model.PeliculaFavorita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculaFavoritaViewModel: ViewModel() {

    private val _peliculaFavorita = MutableLiveData<List<PeliculaFavorita>>()
    val peliculaFavorita: LiveData<List<PeliculaFavorita>> get() = _peliculaFavorita

    private val _favorita = MutableLiveData<PeliculaFavorita>()
    val favorita: LiveData<PeliculaFavorita> get() = _favorita

    private val repository: PeliculaFavoritaRepository by lazy {
        val dao = (MyApplication.instance as MyApplication).room.peliculaFavoritaDao()
        PeliculaFavoritaRepository(dao)
    }

    fun agregarPeliculaFavorita(pelicula: PeliculaFavorita) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.agregarPeliculaFavorita(pelicula)
        }
    }

    fun eliminarPeliculaFavorita(pelicula: PeliculaFavorita) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarPeliculaFavorita(pelicula)
        }
    }
    fun obtenerPeliculasFavoritas() {
        viewModelScope.launch(Dispatchers.IO) {
            val peliculas = repository.obtenerPeliculasFavoritas()
            _peliculaFavorita.postValue(peliculas)
        }
    }
    fun esFavorita(id: Int): LiveData<Boolean> {
        val resultado = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val fav = repository.obtenerPeliculasFavoritas()
            val esFavorita = fav.any { it.id == id } ?: false
            withContext(Dispatchers.Main) {
                resultado.value = esFavorita
            }
        }
        return resultado

    }
}