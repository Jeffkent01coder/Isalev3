package com.jeff.isalev3.network

import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.GetSalesResponse
import com.jeff.isalev3.models.LoginResponse
import com.jeff.isalev3.models.SignUp
import com.jeff.isalev3.models.SignUpResponse
import com.jeff.isalev3.models.getProfomaResponse
import com.jeff.isalev3.models.StockData
import com.jeff.isalev3.ui.auth.changePassword.ChangePassword
import com.jeff.isalev3.ui.auth.changePassword.ChangePasswordRequest
import com.jeff.isalev3.ui.home.stock.additems.model.AddItemData
import com.jeff.isalev3.ui.home.stock.additems.model.AddItemResponse
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object RetrofitService {
     private const val BASE_URL = "https://milajematrix.stanbestgroup.com/"
//      private const val BASE_URL = "https://vibraniumapi.stanbestgroup.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS) // Connect timeout
        .readTimeout(120, TimeUnit.SECONDS)    // Read timeout
        .writeTimeout(120, TimeUnit.SECONDS)   // Write timeout
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body authParams: AuthParams): LoginResponse
    @POST("auth/signup")
    suspend fun signUp(@Body signUpData: SignUp): SignUpResponse

    @GET("sales")
    suspend fun getSales(@Header("Authorization") token: String): GetSalesResponse
    @GET("profoma")
    suspend fun getProfomas(@Header("Authorization") token: String): getProfomaResponse
    @GET("items")
    suspend fun getItems(@Header("Authorization") token: String): StockData
    @POST("auth/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ): ChangePassword
    @POST("items")
    suspend fun addItem(
        @Header("Authorization") token: String,
        @Body addItemData: AddItemData
    ): AddItemResponse



}