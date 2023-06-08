package com.most4dev.recipesapp.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.most4dev.recipesapp.data.impl.ProfileRepositoryImpl.Companion.EMPTY_PROFILE
import com.most4dev.recipesapp.domain.entities.ProfileEntity
import com.most4dev.recipesapp.domain.usecases.GetDataUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getDataUserUseCase: GetDataUserUseCase,
) : ViewModel() {

    private var _profile = MutableLiveData<ProfileEntity>()
    val profile: LiveData<ProfileEntity> = _profile

    fun getCity(fusedLocationClient: FusedLocationProviderClient) {
        viewModelScope.launch(Dispatchers.IO) {
            _profile.postValue(getDataUserUseCase(fusedLocationClient) ?: EMPTY_PROFILE)
        }
    }

}