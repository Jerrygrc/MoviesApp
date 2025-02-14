package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Pelicula

class PeliculaAdapter(
    private var peliculas:MutableList<Pelicula>,
    private val onClickListener:(Pelicula)->Unit):
    RecyclerView.Adapter<PeliculaViewHolder>() {


    fun updateData(newPeliculas: List<Pelicula>){
        var size = peliculas.size
        peliculas.clear()
        notifyItemRangeRemoved(0,size)
        peliculas.addAll(newPeliculas)
        size = peliculas.size
        notifyItemRangeInserted(0,size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula,parent,false)
        return PeliculaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.bind(
            peliculas[position], onClickListener
        )
    }

}