package com.example.mercadolibreprueba.views
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.adapters.AdapterProducts
import com.example.mercadolibreprueba.adapters.OnItemClickListener
import com.example.mercadolibreprueba.databinding.ActivityMainBinding
import com.example.mercadolibreprueba.model.search.Results
import com.example.mercadolibreprueba.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}