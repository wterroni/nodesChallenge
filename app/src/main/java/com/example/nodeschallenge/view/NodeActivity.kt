package com.example.nodeschallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nodeschallenge.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NodeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.helloWorldTextView.text = viewModel.getHelloWord()

    }

}