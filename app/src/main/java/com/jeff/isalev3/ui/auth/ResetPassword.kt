package com.jeff.isalev3.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.ActivityResetPasswordBinding
import com.stanbestgroup.isalev2.Room.RoomApplication

class ResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)


        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(), DataStoreRepository.getInstance(
                    this
                ), (this.application as RoomApplication).repository
            )
        )[AppViewModel::class.java]

        binding.resetPass.setOnClickListener {
            val currentPassword = binding.currentPassword.text.toString()
            val newPassword = binding.newPassword.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            binding.loginProgress.isVisible = true

            viewModel.getCachedDetails()
            viewModel.savedPreferences.observe(this) {

                Log.d("TAG", "token ${it.bearerToken}")
                viewModel.resetPassword(
                    it.bearerToken,
                    currentPassword,
                    newPassword,
                    confirmPassword,
                    onSuccess = { successMessage ->
                        binding.loginProgress.isVisible = false
                        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Login::class.java))
                        finish()
                    },
                    onError = { errorMessage ->
                        binding.loginProgress.isVisible = false
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                )
            }

        }
    }

//    private fun getAuthToken(): String {
//        // Return the stored auth token for API authentication
//        return "your_auth_token"
//    }
}
