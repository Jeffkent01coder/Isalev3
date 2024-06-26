package com.jeff.isalev3.network

import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.GetSalesResponse
import com.jeff.isalev3.models.LoginResponse
import com.jeff.isalev3.models.getProfomaResponse
import com.jeff.isalev3.models.StockData
import okhttp3.OkHttpClient
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
    @GET("sales")
    suspend fun getSales(@Header("Authorization") token: String): GetSalesResponse
    @GET("profoma")
    suspend fun getProfomas(@Header("Authorization") token: String): getProfomaResponse
    @GET("items")
    suspend fun getItems(@Header("Authorization") token: String): StockData

}