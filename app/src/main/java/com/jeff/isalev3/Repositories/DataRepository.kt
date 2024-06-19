package com.jeff.isalev3.Repositories

import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.GetSalesResponse
import com.jeff.isalev3.models.LoginResponse
import com.jeff.isalev3.models.getProfomaResponse
import com.jeff.isalev3.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository {

    suspend fun loginUser(authParams: AuthParams): LoginResponse {
        return withContext(Dispatchers.IO) {
            RetrofitService.retrofitService.login(authParams)
        }
    }

    suspend fun getAllSales(token: String): GetSalesResponse {
        return withContext(Dispatchers.IO) {
            RetrofitService.retrofitService.getSales("Bearer " + token)

        }
    }

    suspend fun getAllProfomas(token: String): getProfomaResponse {
        return withContext(Dispatchers.IO) {
            RetrofitService.retrofitService.getProfomas("Bearer " + token)
        }
    }

}