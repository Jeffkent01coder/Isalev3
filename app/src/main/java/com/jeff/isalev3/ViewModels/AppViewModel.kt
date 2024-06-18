package com.jeff.isalev3.ViewModels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.jeff.isalev3.Repositories.AccountData
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.models.AuthParams
import com.jeff.isalev3.models.AuthUIState
import com.jeff.isalev3.models.LoginResponse
import com.jeff.isalev3.models.getProfomaUIState
import com.jeff.isalev3.models.getSalesUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.EnumMap

class AppViewModel (
    private val dataRepository: DataRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {


    private var _authUIState: MutableLiveData<AuthUIState> = MutableLiveData()
    val authUIState get() = _authUIState

    val savedPreferences = MutableLiveData<AccountData>()


    private var _getSalesUIState: MutableLiveData<getSalesUIState> = MutableLiveData()
    val getSalesUIState: MutableLiveData<getSalesUIState> get() = _getSalesUIState

    private var _getProfomasUIState: MutableLiveData<getProfomaUIState> = MutableLiveData()
    val getProfomasUIState: MutableLiveData<getProfomaUIState> get() = _getProfomasUIState

    private val _qrCode = MutableLiveData<Bitmap>()
    val qrCode: LiveData<Bitmap> get() = _qrCode

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

    fun getSales(token: String) {
        viewModelScope.launch {
            try {
                val res = dataRepository.getAllSales(token)
                Log.d("my sales", res.toString())

                _getSalesUIState.value =
                    getSalesUIState("", res.message.toString(), res.status.toString(), res)
                Log.d("my sales", res.toString())
            } catch (e: Exception) {
                Log.d("my sales exception", e.message.toString())
                _getSalesUIState.value = getSalesUIState(e.message, null, null, null)
            }
        }
    }

    fun generateQR(data: String, color: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val hints: MutableMap<EncodeHintType, Any> = EnumMap(EncodeHintType::class.java)
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            val qrCodeWriter = QRCodeWriter()
            val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 512, 512, hints)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) -0x1000000 else 0x10FFFFFF)
                }
            }
            _qrCode.value = bitmap

        }
    }
    fun getCachedDetails() {
        viewModelScope.launch {
            dataStoreRepository.getAccountData().collectLatest {
                savedPreferences.value = it
            }
        }
    }

    fun getProfomas(token: String) {
        viewModelScope.launch {
            try {
                val res = dataRepository.getAllProfomas(token)
                Log.d("my profomas", res.toString())

                _getProfomasUIState.value =
                    getProfomaUIState("", res.message.toString(), res.status.toString(), res)
                Log.d("my profomas", res.toString())
            } catch (e: Exception) {
                Log.d("my profomas exception", e.message.toString())
                _getProfomasUIState.value = getProfomaUIState(e.message, null, null, null)
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