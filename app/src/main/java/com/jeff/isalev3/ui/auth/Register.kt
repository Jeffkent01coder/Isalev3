package com.jeff.isalev3.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jeff.isalev3.databinding.ActivityRegisterBinding
import org.json.JSONObject

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
        binding.lnRegisterStep5.visibility = View.GONE
        binding.lnRegisterStep6.visibility = View.GONE
        binding.lnRegisterStep7.visibility = View.GONE

        binding.btnRegisterNext.setOnClickListener {
//
//            //Store data
//            if(!binding.edtRegisterBusinessName.text.isNullOrEmpty() && !binding.edtRegisterBusinessPin.text.isNullOrEmpty()) {
//                signObj.put("business_name", binding.edtRegisterBusinessName.text.toString())
//                signObj.put("kra_pin", binding.edtRegisterBusinessPin.text.toString())
//                signObj.put("business_address", binding.edtRegisterBusinessAddress.text.toString())
//                signObj.put("business_phone", binding.edtRegisterBusinessMobile.text.toString())
//                signObj.put("business_email", binding.edtRegisterBusinessEmail.text.toString())
//                signObj.put("business_nature", binding.spnRegisterNature.selectedItem.toString())

            binding.txtRegisterStepTitle.text = "Step 2/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.VISIBLE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
            //}
            //            else {
//                Toast.makeText(this@RegisterActivity, "Enter the business name and pin", Toast.LENGTH_SHORT).show()
//            }
        }
        binding.btnRegisterPrevious2.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 1/5"
            binding.lnRegisterStep1.visibility = View.VISIBLE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }

        binding.btnRegisterNext2.setOnClickListener {
            //Store data
//            if(!binding.edtRegisterBusinessOwnerName.text.isNullOrEmpty() && !binding.edtRegisterBusinessOwnerMobile.text.isNullOrEmpty() && !binding.edtRegisterBusinessOwnerPassport.text.isNullOrEmpty()) {
//                signObj.put("first_name", binding.edtRegisterBusinessOwnerName.text.toString())
//                signObj.put("phone", binding.edtRegisterBusinessOwnerMobile.text.toString())
//                signObj.put("email", binding.edtRegisterBusinessOwnerEmail.text.toString())
//                signObj.put("username", binding.edtRegisterBusinessOwnerUsername.text.toString())
//                signObj.put("password", binding.edtRegisterBusinessOwnerPassword.text.toString())
//                signObj.put("nationalid", binding.edtRegisterBusinessOwnerPassport.text.toString())
//                signObj.put("position", binding.edtRegisterBusinessOwnerPosition.text.toString())
//                signObj.put("address", binding.edtRegisterBusinessOwnerAddress.text.toString())

            binding.txtRegisterStepTitle.text = "Step 3/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.VISIBLE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
//            } else {
//                Toast.makeText(this@RegisterActivity, "Enter the owner information", Toast.LENGTH_SHORT).show()
//            }
        }
        binding.btnRegisterPrevious3.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 2/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.VISIBLE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }

        binding.btnRegisterNext3.setOnClickListener {
//            signObj.put("device", binding.spnRegisterType.selectedItem.toString())

            binding.txtRegisterStepTitle.text = "Step 4/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.VISIBLE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }
        binding.btnRegisterPrevious4.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 3/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.VISIBLE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }
        binding.btnRegisterNext4.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 5/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.VISIBLE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }

        binding.btnRegisterPrevious5.setOnClickListener {
            binding.txtRegisterStepTitle.text = "Step 4/5"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.VISIBLE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.GONE
        }

//        val jsonAccount = JSONObject()
        binding.btnRegisterNext5.setOnClickListener {
            //Submit Registration
//            signObj.put("source", binding.spnRegisterSource.selectedItem.toString())
//            signObj.put("source_agent", "")
//            binding.btnRegisterNext5.setText("Registering ....")
//            binding.btnRegisterNext5.isEnabled = false
//            if(binding.spnRegisterSource.selectedItemPosition == 0){
//                signObj.put("source_agent", codeAgents)
//            }
//            if (licenceCode.isNullOrEmpty()){
//                Toast.makeText(this, "Select a licence ", Toast.LENGTH_SHORT).show()
//                binding.btnRegisterNext5.setText("Submit Registration")
//                binding.btnRegisterNext5.isEnabled = true
//            }else {
//                signObj.put("licence_id", licenceCode)
//                signObj.put("licence_count", binding.edtSignInLicenseNumber.text.toString().toInt())
//
//                GlobalScope.launch {
//                    try {
//                        Log.e("Signup", "Api called")
//                        val requestBod = signObj.toString()
//                        val requestBody =
//                            RequestBody.create(MediaType.parse("application/json"), requestBod)
//
//                        Log.e("Signup string", "" + requestBod)
//                        val responseBody = ApiServer.apiService.signup(requestBody)
//                        val responseJson = JSONObject(responseBody.string())
//                        Log.e("Signup Response", "=" + responseJson.toString())
//                        if (responseJson.getBoolean("status")) {
//                            runOnUiThread {
//                                binding.btnRegisterNext5.setText("Submit Registration")
//                                binding.btnRegisterNext5.isEnabled = true
//
//                                jsonAccount.put("account", responseJson.getString("account"))
//
////                                binding.txtPaymentAccount.text = "${responseJson.getString("account")}-$licenceCodePayment"
//                                binding.txtPaymentAccount.text = "${responseJson.getString("account")}"

            binding.txtRegisterStepTitle.visibility = View.GONE
            binding.txtRegistrationTitle.text = "Payment"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.VISIBLE
            binding.lnRegisterStep7.visibility = View.GONE
        }
//                        } else {
//                            runOnUiThread {
//                                binding.btnRegisterNext5.setText("Submit Registration")
//                                binding.btnRegisterNext5.isEnabled = true
//                            }
//                        }
//                    } catch (exception: Exception) {
//                        exception.printStackTrace()
//                        runOnUiThread {
//                            binding.btnRegisterNext5.setText("Submit Registration")
//                            binding.btnRegisterNext5.isEnabled = true
//                        }
//                    }
//                }
//            }

        // }
        binding.btnRegisterNext6.setOnClickListener {
            binding.txtRegisterStepTitle.visibility = View.GONE
            binding.txtRegistrationTitle.text = "Payment"
            binding.lnRegisterStep1.visibility = View.GONE
            binding.lnRegisterStep2.visibility = View.GONE
            binding.lnRegisterStep3.visibility = View.GONE
            binding.lnRegisterStep4.visibility = View.GONE
            binding.lnRegisterStep5.visibility = View.GONE
            binding.lnRegisterStep6.visibility = View.GONE
            binding.lnRegisterStep7.visibility = View.VISIBLE
        }

        binding.btnRegisterNext7.setOnClickListener {
//            GlobalScope.launch {
//                try{
//                    Log.e("Verify", "Api called")
//                    val requestBod = jsonAccount.toString()
//                    val requestBody = RequestBody.create(MediaType.parse("application/json"), requestBod)
//
//                    val responseBody = ApiServer.apiService.verify(requestBody)
//                    val responseJson = JSONObject(responseBody.string())
//                    Log.e("Verify Response", "=" + responseJson.toString())
//                    if (responseJson.getBoolean("status")) {
//                        runOnUiThread {
//                            Toast.makeText(this@RegisterActivity, "Payment received successfully.", Toast.LENGTH_SHORT).show()
//                        }
//                        startActivity(Intent(this@RegisterActivity, SignInActivity::class.java))
//                        finish()
//                    } else {
//                        runOnUiThread {
//                            Toast.makeText(this@RegisterActivity, "Payment not found. Try again", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }catch (e:Exception){
//                    e.printStackTrace()
//                    runOnUiThread {
//                        Toast.makeText(this@RegisterActivity, "Payment not found. Try again", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }

        }

    }
}