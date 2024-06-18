package com.jeff.isalev3.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jeff.isalev3.Repositories.AccountData
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.AuthUIState
import com.jeff.isalev3.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class AppViewModel (
    private val dataRepository: DataRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {


    private var _authUIState: MutableLiveData<AuthUIState> = MutableLiveData()
    val authUIState get() = _authUIState

    val savedPreferences = MutableLiveData<AccountData>()

    fun loginUser(authParams: AuthParams) {
        viewModelScope.launch {
            try {
                val res = dataRepository.loginUser(authParams)

                if (res.status) {
                    _authUIState.value = AuthUIState(null, "true", res)
                } else {
                    _authUIState.value =
                        AuthUIState("Password is not correct. Try again later", null, null)
                }
            } catch (e: Exception) {
                _authUIState.value = AuthUIState(e.message, null, null)
            }
        }
    }

    fun cacheDetails(data: LoginResponse, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.writeToDataStore(data, username)
        }
    }

}

class StateViewModelFactory(
    private val dataRepository: DataRepository,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(dataRepository, dataStoreRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}