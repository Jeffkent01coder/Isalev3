package com.jeff.isalev3.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.MainActivity
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.ActivityLoginBinding
import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.ui.home.Home

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, StateViewModelFactory(
            DataRepository(), DataStoreRepository.getInstance(applicationContext))
        )[AppViewModel::class.java]

        binding.signIn.setOnClickListener {
            binding.loginProgress.visibility = View.VISIBLE
            viewModel.loginUser(
                AuthParams(binding.businessPin.text.toString().trim(),
                binding.username.text.toString().trim(),binding.password.text.toString().trim())
            )
            viewModel.authUIState.observe(this) {state->
                Log.d("Login state",state.toString())
                state.errorMessage?.let {
                    binding.loginProgress.visibility = View.GONE
                }

                state.successful?.let {
                    //write to data store
                    binding.loginProgress.visibility = View.GONE
                    viewModel.cacheDetails(state.observableLoginData!!,binding.username.text.toString().trim())
                    startActivity(Intent(this, Home::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))

                }

            }
        }

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
            finish()
        }
    }
}