package com.jeff.isalev3.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jeff.isalev3.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.txtRegisterStepTitle.text = "Step 1/5"
        binding.lnRegisterStep1.visibility = View.VISIBLE
        binding.lnRegisterStep2.visibility = View.GONE
        binding.lnRegisterStep3.visibility = View.GONE
        binding.lnRegisterStep4.visibility = View.GONE


        binding.btnRegisterNext.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 2/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.VISIBLE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE

        }
        binding.btnBack2.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 1/5"
            binding.lnRegisterStep1.visibility = View.VISIBLE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
        }

        binding.btnRegisterNext2.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 3/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.VISIBLE
            binding.lnRegisterStep4.visibility = View.GONE

        }
        binding.btnBack3.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 2/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.VISIBLE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
        }

        binding.btnRegisterNext3.setOnClickListener {

            binding.txtRegisterStepTitle.text = "Step 4/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.VISIBLE
        }
        binding.btnBack4.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 3/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.VISIBLE
            binding.lnRegisterStep4.visibility = View.GONE
        }
        binding.btnRegisterNext4.setOnClickListener {
            startActivity(Intent(this, SignUpConfirmation::class.java))
        }

    }
}