package com.example.movies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movies.databinding.ItemPeliculaBinding
import com.example.movies.model.Pelicula
import com.squareup.picasso.Picasso

class PeliculaViewHolder(view: View): ViewHolder(view) {
    private val binding = ItemPeliculaBinding.bind(view)

    fun bind(pelicula: Pelicula, onClickListener: (Pelicula) -> Unit){
        binding.tvMovieTitleInsideCv.text = pelicula.title
        Picasso.get().load("https://image.tmdb.org/t/p/w500${pelicula.posterPath}")
            .into(binding.ivMoviesPoster)
            itemView.setOnClickListener{onClickListener(pelicula)}

        binding.root.setOnClickListener {
            onClickListener(pelicula)
        }
    }
}