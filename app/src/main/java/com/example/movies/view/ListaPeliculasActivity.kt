package com.example.movies.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.adapter.PeliculaAdapter
import com.example.movies.data.remote.RetrofitClient
import com.example.movies.data.repository.PeliculaRepository
import com.example.movies.databinding.ActivityListaPeliculasBinding
import com.example.movies.model.Pelicula
import com.example.movies.viewmodel.PeliculaFavoritaViewModel
import com.example.movies.viewmodel.PeliculaViewModel

class ListaPeliculasActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityListaPeliculasBinding
    private val binding: ActivityListaPeliculasBinding get() = _binding

    private lateinit var viewModel: PeliculaViewModel
    private lateinit var adapter: PeliculaAdapter

    private lateinit var favoritasViewModel: PeliculaFavoritaViewModel
    private var mostrandoFavoritas = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        favoritasViewModel = ViewModelProvider(this)[PeliculaFavoritaViewModel::class.java]

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val listaPelis = mutableListOf<Pelicula>()
        adapter = PeliculaAdapter(listaPelis){ pelicula ->
            onItemSelected(pelicula)
        }
        binding.rvMovies.adapter = adapter

        val apiService = RetrofitClient.apiService
        val repository = PeliculaRepository(apiService)
        viewModel = PeliculaViewModel(repository)

        viewModel.cargarPeliculasEnCartelera(1)
        binding.toolbar.setTitle("En Cartelera")

        viewModel.peliculas.observe(this) { peliculas ->
            if (!peliculas.isNullOrEmpty()) {
                adapter.updateData(peliculas)
                binding.rvMovies.visibility = View.VISIBLE
                binding.tvSinPelis.visibility = View.GONE
            } else {
                binding.rvMovies.visibility = View.GONE
                binding.tvSinPelis.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cartelera -> {
                viewModel.cargarPeliculasEnCartelera(1)
                binding.toolbar.setTitle("En Cartelera")
                return true
            }
            R.id.action_populares -> {
                viewModel.cargarPeliculasPopulares(1)
                binding.toolbar.setTitle("Populares")
                return true
            }
            R.id.action_mejor_valoradas -> {
                viewModel.cargarPeliculasMasValoradas(1)
                binding.toolbar.setTitle("Mejor Valoradas")
                return true
            }
            R.id.action_proximamente -> {
                viewModel.cargarProximasPeliculas(1)
                binding.toolbar.setTitle("Próximamente")
                return true
            }
            R.id.action_favoritas -> {
                mostrandoFavoritas = true
                binding.toolbar.setTitle("Películas Favoritas")
                val favoritoViewModel = ViewModelProvider(this)[PeliculaFavoritaViewModel::class.java]
                favoritoViewModel.obtenerPeliculasFavoritas()
                favoritoViewModel.peliculaFavorita.observe(this) { favoritas ->
                    if (!favoritas.isNullOrEmpty()) {
                        val peliculasFavoritas = favoritas.map { favorito ->
                            Pelicula(
                                id = favorito.id,
                                title = favorito.title,
                                originalTitle = favorito.originalTitle,
                                overview = favorito.overview,
                                posterPath = favorito.posterPath,
                                backdropPath = favorito.backdropPath,
                                releaseDate = favorito.releaseDate,
                                voteAverage = favorito.voteAverage
                            )
                        }
                        adapter.updateData(peliculasFavoritas)
                        binding.rvMovies.visibility = View.VISIBLE
                        binding.tvSinPelis.visibility = View.GONE
                    }else{
                        binding.rvMovies.visibility = View.GONE
                        binding.tvSinPelis.visibility = View.VISIBLE
                    }
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun onItemSelected(pelicula: Pelicula) {
        val intent = Intent(this, DetallesActivity::class.java)
        intent.putExtra("pelicula", pelicula)
        this.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if (mostrandoFavoritas) {
            favoritasViewModel.obtenerPeliculasFavoritas()
        }
    }
}