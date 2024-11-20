package com.jeff.isalev3.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val success: Boolean,
    val message: String,
    val signUpData: SignUp?
)

data class SignUpAuthUIState(
    val errorMessage: String?,
    val successMessage: String?,
    val signUpResponse: SignUpResponse?
)
