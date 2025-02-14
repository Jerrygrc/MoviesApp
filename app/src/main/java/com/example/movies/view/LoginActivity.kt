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
import com.example.movies.databinding.ActivityLoginBinding
import com.example.movies.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding
    private val binding: ActivityLoginBinding get() = _binding

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioViewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        usuarioViewModel.usuario.observe(this) { usuario ->
            if (usuario != null) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListaPeliculasActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLoginCreateAccount.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            val nombre = binding.etLoginUsername.text.toString().trim()
            val password = binding.etLoginPwd.text.toString().trim()

            usuarioViewModel.loginUsuario(nombre, password)


        }
    }
}