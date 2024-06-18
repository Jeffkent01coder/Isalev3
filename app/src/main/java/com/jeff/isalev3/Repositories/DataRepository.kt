package com.jeff.isalev3.Repositories

import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.LoginResponse
import com.jeff.isalev3.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository {

    suspend fun loginUser(authParams: AuthParams): LoginResponse {
        return withContext(Dispatchers.IO){
            RetrofitService.retrofitService.login(authParams)
        }
    }

}