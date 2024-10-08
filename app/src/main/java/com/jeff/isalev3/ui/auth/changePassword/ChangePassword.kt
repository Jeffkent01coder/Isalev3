package com.jeff.isalev3.ui.auth.changePassword


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePassword(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)

