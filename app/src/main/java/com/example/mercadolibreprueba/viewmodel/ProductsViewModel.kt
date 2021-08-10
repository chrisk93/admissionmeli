package com.example.mercadolibreprueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mercadolibreprueba.api.ServiceApi
import com.example.mercadolibreprueba.model.controller.Status
import com.example.mercadolibreprueba.model.search.Base
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val service: ServiceApi) :ViewModel() {

    private val _products = MutableLiveData<Base?>()
    private val products: LiveData<Base?> get() = _products

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    private val isLoading : LiveData<Boolean> get() = _isLoading

    private val _showError = MutableLiveData<String>()
    private val showError : LiveData<String> get() = _showError

    suspend fun searchProducts(query: String) {
        val dataProds = service.searchItems(query)
        _isLoading.value = false
        if (dataProds.status.name != Status.ERROR.name) {
            _products.postValue(dataProds.data)
        }else {
            _showError.postValue(dataProds.message ?: "")
        }
    }

    suspend fun getProducts() : LiveData<Base?> = products

    fun getIsLoading() : LiveData<Boolean> = isLoading

    fun getError()  : LiveData<String>  = showError

    fun showProgress(show:Boolean) {
        _isLoading.value = show
    }
}