package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.ActivityDetallesBinding
import com.example.movies.model.Pelicula
import com.example.movies.model.PeliculaFavorita
import com.example.movies.viewmodel.DetallesViewModel
import com.example.movies.viewmodel.PeliculaFavoritaViewModel
import com.squareup.picasso.Picasso

class DetallesActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetallesBinding
    private val binding: ActivityDetallesBinding get() = _binding
    private var esFavorita = false

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this)[DetallesViewModel::class.java]
        val favoritoViewModel = ViewModelProvider(this)[PeliculaFavoritaViewModel::class.java]

        val pelicula = intent.getParcelableExtra<Pelicula>("pelicula")
        pelicula?.let { viewModel.cargarPelicula(it) }

        viewModel.pelicula.observe(this) { pelicula ->
            Picasso.get().load("https://image.tmdb.org/t/p/w500${pelicula.backdropPath}")
                .into(binding.ivPosterPeli)
            binding.tvMovieTitle.text = pelicula.title
            binding.tvOriginalTitleDisplay.text = pelicula.originalTitle
            binding.tvSinopsisText.text = pelicula.overview
            binding.tvFechaEstreno.text = pelicula.releaseDate
            binding.tvValoracionPublico.text = pelicula.voteAverage.toString()

            favoritoViewModel.esFavorita(pelicula.id).observe(this) { favorito ->
                esFavorita = favorito
                actualizarIconoEstrella()
            }
        }
        binding.btnEstrella.setOnClickListener {

            if(!esFavorita){
                binding.btnEstrella.setImageResource(R.drawable.ic_estrella_llena)
                pelicula?.let { peli ->
                val peliculaFavorita = PeliculaFavorita(
                    id = peli.id,
                    title = peli.title,
                    originalTitle = peli.originalTitle,
                    overview = peli.overview,
                    posterPath = peli.posterPath,
                    backdropPath = peli.backdropPath,
                    releaseDate = peli.releaseDate,
                    voteAverage = peli.voteAverage
                )
                    favoritoViewModel.agregarPeliculaFavorita(peliculaFavorita)
                    esFavorita = true
                    Toast.makeText(this, "Pelicula agregada a favoritos", Toast.LENGTH_SHORT).show()
                }
            }else {
                binding.btnEstrella.setImageResource(R.drawable.ic_estrella_vacia)
                pelicula?.let { peli ->
                    val favorito = PeliculaFavorita(
                        id = peli.id,
                        title = peli.title,
                        originalTitle = peli.originalTitle,
                        overview = peli.overview,
                        posterPath = peli.posterPath,
                        backdropPath = peli.backdropPath,
                        releaseDate = peli.releaseDate,
                        voteAverage = peli.voteAverage
                    )
                    favoritoViewModel.eliminarPeliculaFavorita(favorito)
                    esFavorita = false
                    Toast.makeText(this, "Pelicula eliminada de favoritos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun actualizarIconoEstrella() {
        if (esFavorita) {
            binding.btnEstrella.setImageResource(R.drawable.ic_estrella_llena)
        } else {
            binding.btnEstrella.setImageResource(R.drawable.ic_estrella_vacia)
        }
    }
}