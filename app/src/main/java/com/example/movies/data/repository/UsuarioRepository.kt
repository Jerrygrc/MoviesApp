package com.example.movies.data.repository

import com.example.movies.data.local.UsuarioDao
import com.example.movies.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrarUsuario(usuario: Usuario) {
        usuarioDao.registrarUsuario(usuario)
    }

    suspend fun loginUsuario(nombre: String, password: String): Usuario? {
        return usuarioDao.loginUsuario(nombre, password)
    }

}