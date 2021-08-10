package com.example.mercadolibreprueba

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mercadolibreprueba.api.ServiceApi
import com.example.mercadolibreprueba.model.controller.ResponseHandler
import com.example.mercadolibreprueba.model.search.Base
import com.example.mercadolibreprueba.model.search.Results
import com.example.mercadolibreprueba.viewmodel.ProductsViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ViewmodelTest {

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var service: ServiceApi
    private lateinit var resp: ResponseHandler

    val result1 = Results(
        id = "1", title = "mytitulo", accepts_mercadopago = true,
        address = null, catalog_listing = true, installments = null, seller = null,
        shipping = null, seller_address = null)

    val result2 = Results(
        id = "2", title = "mytitulo2", accepts_mercadopago = true,
        address = null, catalog_listing = true, installments = null, seller = null,
        shipping = null, seller_address = null)

    val myList = listOf<Results>(result1, result2)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context: Context = mock(Context::class.java)
        resp = ResponseHandler(context)
        service = mock(ServiceApi::class.java)

        productsViewModel = ProductsViewModel(service)
    }

    @Test
    fun searchProduct() = runBlocking {

        Mockito.`when`(service.searchItems("perro")).thenReturn(resp.handleSuccess(Base("", "", null, myList)))

        productsViewModel.searchProducts("perro")
        val result = productsViewModel.getProducts().value
        assertThat(result).isEqualTo(Base("", "", null, myList))

    }
}