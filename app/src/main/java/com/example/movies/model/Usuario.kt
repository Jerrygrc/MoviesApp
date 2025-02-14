package com.example.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val password: String
)
