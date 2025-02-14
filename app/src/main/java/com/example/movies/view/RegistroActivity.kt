package com.example.movies.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.ActivityRegistroBinding
import com.example.movies.model.Usuario
import com.example.movies.viewmodel.UsuarioViewModel

class RegistroActivity : AppCompatActivity() {

    private lateinit var _binging: ActivityRegistroBinding
    private val binding: ActivityRegistroBinding get() = _binging

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binging = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioViewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        usuarioViewModel.registroState.observe(this){ success ->
            if(success){
                Toast.makeText(this, "Bienvenido. Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCrearCuenta.setOnClickListener {
            val nombre = binding.etRegistroUsername.text.toString().trim()
            val password = binding.etRegistroPwd.text.toString().trim()

            val usuario = Usuario(0,nombre, password)
            usuarioViewModel.registrarUsuario(usuario)
        }
    }
}