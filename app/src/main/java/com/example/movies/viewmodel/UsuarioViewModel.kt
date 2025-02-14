package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.MyApplication
import com.example.movies.data.repository.UsuarioRepository
import com.example.movies.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val database = (application as MyApplication).room
    private val usuarioDao = database.usuarioDao()

    private val repository = UsuarioRepository(usuarioDao)

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: MutableLiveData<Usuario?> = _usuario

    private val _registroState = MutableLiveData<Boolean>()
    val registroState: MutableLiveData<Boolean> = _registroState

    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repository.registrarUsuario(usuario)
                    withContext(Dispatchers.Main){
                        _registroState.value = true
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _registroState.value = false
                }
            }
        }
    }
    fun loginUsuario(nombre: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.loginUsuario(nombre, password)
            withContext(Dispatchers.Main) {
                _usuario.value = user
            }
        }
    }
}