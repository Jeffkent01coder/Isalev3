package com.jeff.isalev3.Repositories

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.BEARER_TOKEN
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_BHFID
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_BHFNAME
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_DVC
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_EMAIL
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_MANAGER
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_MANAGER_EMAIL
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_MANAGER_PHONE
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_MRC
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_NAME
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_PHONE
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_PROVINCE
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_SCU
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_STREET
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.ISALE_TIN
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.NUMBER_OF_SALES
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.TOTAL_SALES
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.TOTAL_TAX
import com.jeff.isalev3.Repositories.DataStoreRepository.PreferenceKeys.USER_NAME
import com.jeff.isalev3.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataStoreRepository(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    suspend fun getAccountData(): Flow<AccountData> {
        return context.dataStore.data.map { preferences ->
            AccountData(
                username = preferences[USER_NAME]?:"",
                bearerToken = preferences[BEARER_TOKEN] ?: "",
                tin = preferences[ISALE_TIN] ?: "",
                name = preferences[ISALE_NAME] ?: "",
                branchId = preferences[ISALE_BHFID] ?: "",
                scu = preferences[ISALE_SCU] ?: "",
                street = preferences[ISALE_STREET] ?: "",
                province = preferences[ISALE_PROVINCE] ?: "",
                email = preferences[ISALE_EMAIL] ?: "",
                phone = preferences[ISALE_PHONE] ?: "",
                branchName = preferences[ISALE_BHFNAME] ?: "",
                deviceId = preferences[ISALE_DVC] ?: "",
                mrcNo = preferences[ISALE_MRC] ?: "",
                managerName = preferences[ISALE_MANAGER] ?: "",
                managerPhone = preferences[ISALE_MANAGER_PHONE] ?: "",
                managerEmail = preferences[ISALE_MANAGER_EMAIL] ?: "",
                totalSales = preferences[TOTAL_SALES] ?: "",
                numberOfSales = preferences[NUMBER_OF_SALES] ?: "",
                totalTax = preferences[TOTAL_TAX] ?: ""
            )
        }
    }
    suspend fun writeToDataStore(d: LoginResponse, username: String):Boolean {
        return withContext(Dispatchers.IO){
            try {
                context.dataStore.edit {preferences->
                    preferences[BEARER_TOKEN] = d.access_token.toString()
                    preferences[ISALE_TIN] = d.business?.pin.toString()
                    preferences[ISALE_NAME] = d.business?.taxprNm.toString()
                    preferences[ISALE_BHFID] =d.business?.branch.toString()
                    preferences[ISALE_SCU] =d.business?.sdcId.toString()
                    preferences[ISALE_STREET] = d.business?.sctrNm.toString()
                    preferences[ISALE_PROVINCE] =d.business?.prvncNm.toString()
                    preferences[ISALE_EMAIL] = d.business?.email.toString()
                    preferences[ISALE_PHONE] = d.business?.phone.toString()
                    preferences[ISALE_BHFNAME] =d.business?.bhfNm.toString()
                    preferences[ISALE_DVC] = d.business?.dvcId.toString()
                    preferences[ISALE_MRC] =d.business?.mrcNo.toString()
                    preferences[ISALE_MANAGER] = d.business?.mgrNm.toString()
                    preferences[ISALE_MANAGER_PHONE] =d.business?.mgrTelNo.toString()
                    preferences[ISALE_MANAGER_EMAIL] =d.business?.mgrEmail.toString()
                    preferences[TOTAL_SALES] =d.stats?.totalSales.toString()
                    preferences[NUMBER_OF_SALES] = d.stats?.numberOfSales.toString()
                    preferences[TOTAL_TAX] = d.stats?.totalTax.toString()
                    preferences[USER_NAME]= username
                }
                true
            }
            catch (e:Exception){
                false
            }
        }

    }

    private object PreferenceKeys {
        val USER_NAME = stringPreferencesKey("USERNAME")
        val BEARER_TOKEN = stringPreferencesKey("BEARERTOKEN")
        val ISALE_TIN = stringPreferencesKey("ISALE_TIN")//business pin
        val ISALE_NAME = stringPreferencesKey("ISALE_NAME")
        val ISALE_BHFID = stringPreferencesKey("ISALE_BHFID")
        val ISALE_SCU = stringPreferencesKey("ISALE_SCU")
        val ISALE_STREET = stringPreferencesKey("ISALE_STREET")
        val ISALE_PROVINCE = stringPreferencesKey("ISALE_PROVINCE")
        val ISALE_EMAIL = stringPreferencesKey("ISALE_EMAIL")
        val ISALE_PHONE = stringPreferencesKey("ISALE_PHONE")
        val ISALE_BHFNAME = stringPreferencesKey("ISALE_BHFNAME")
        val ISALE_DVC = stringPreferencesKey("ISALE_DVC")
        val ISALE_MRC = stringPreferencesKey("ISALE_MRC")
        val ISALE_MANAGER = stringPreferencesKey("ISALE_MANAGER")
        val ISALE_MANAGER_PHONE = stringPreferencesKey("ISALE_MANAGER_PHONE")
        val ISALE_MANAGER_EMAIL = stringPreferencesKey("ISALE_MANAGER_EMAIL")
        val TOTAL_SALES = stringPreferencesKey("totalSales")
        val NUMBER_OF_SALES = stringPreferencesKey("numberOfSales")
        val TOTAL_TAX = stringPreferencesKey("totalTax")
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: DataStoreRepository? = null
        fun getInstance(context: Context): DataStoreRepository {
            return instance ?: synchronized(this) {
                instance ?: DataStoreRepository(context).also { instance = it }
            }
        }
        private const val USER_PREFERENCES_NAME = "isale_account_preferences"
    }
}

data class AccountData(
    val username:String,
    val bearerToken: String,
    val tin: String,
    val name: String,
    val branchId: String,
    val scu: String,
    val street: String,
    val province: String,
    val email: String,
    val phone: String,
    val branchName: String,
    val deviceId: String,
    val mrcNo: String,
    val managerName: String,
    val managerPhone: String,
    val managerEmail: String,
    val totalSales: String,
    val numberOfSales: String,
    val totalTax: String
)