package com.gilsoft.agencetest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gilsoft.agencetest.MainActivity
import com.gilsoft.agencetest.R
import com.gilsoft.agencetest.databinding.ActivityLoginBinding
import com.gilsoft.agencetest.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun logIn(view: View) {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}