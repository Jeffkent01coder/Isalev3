package com.jeff.isalev3.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.ActivityRegisterBinding
import com.jeff.isalev3.models.SignUp

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
        binding.btnregister.setOnClickListener {
            binding.loginProgress.visibility = View.VISIBLE

            // Retrieve the selected licence type
            val selectedLicenceType = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.rbStandardLicence -> "Standard Licence (6000)"
                R.id.rbDeviceWithoutLicence -> "Device without Licence (30000)"
                R.id.rbLicenceWithDevice -> "Licence with Device (35000)"
                else -> "Unknown" // default value in case no radio button is selected
            }

            val signUp = SignUp(
                business_name = binding.businessNameEt.text.toString().trim(),
                kra_pin = binding.kraPinEt.text.toString().trim(),
                business_email = binding.businessEmailEt.text.toString().trim(),
                business_phone = binding.businessPhoneEt.text.toString().trim(),
                business_address = binding.businessAddress.text.toString().trim(),
                business_nature = binding.businessNature.text.toString().trim(),
                first_name = binding.firstNameEt.text.toString().trim(),
                username = binding.userNameEt.text.toString().trim(),
                password = binding.passwordEt.text.toString().trim(),
                position = binding.positionEt.text.toString().trim(),
                address = binding.addressEt.text.toString().trim(),
                phone = binding.businessPhoneEt.text.toString().trim(),
                email = binding.emailEt.text.toString().trim(),
                nationalid = binding.nationalIDEt.text.toString().trim(),
                licence_id = selectedLicenceType,  // Pass the selected licence type here
                licence_count = binding.noOfLicenceDevices.text.toString().toIntOrNull() ?: 0,
                branch = binding.branches.text.toString().trim()
            )


            val intent = Intent(this, SignUpConfirmation::class.java)
            intent.putExtra("signUp", signUp)
            startActivity(intent)
            finish()



        }

    }
}