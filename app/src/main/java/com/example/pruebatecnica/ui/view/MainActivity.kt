package com.example.pruebatecnica.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener{
            binding.textCode.text = ""
        }

        binding.buttonGo.setOnClickListener{
            val intent = Intent(this@MainActivity, RecallActivity::class.java)
            startActivity(intent)
        }
    }

    fun sendNumber(view: View){
        val button = view as Button
        val textCode = binding.textCode
        val code: String = textCode.text.toString() + button.text.toString()
        textCode.text = code
    }

}