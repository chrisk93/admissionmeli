package com.example.mercadolibreprueba

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mercadolibreprueba.api.ServiceApi
import com.example.mercadolibreprueba.model.controller.Resource
import com.example.mercadolibreprueba.model.controller.ResponseHandler
import com.example.mercadolibreprueba.model.controller.Status
import com.example.mercadolibreprueba.model.search.Base
import com.example.mercadolibreprueba.model.search.Results
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class ApiTest {

    private lateinit var serviceApi: ServiceApi
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

    @Before
    fun setUp(){
        val context: Context = Mockito.mock(Context::class.java)
        serviceApi =  Mockito.mock(ServiceApi::class.java)
        resp = ResponseHandler(context)
    }

    @Test fun loadProds() = runBlocking {

        val resp = resp.handleSuccess(Base("", "hola", null, myList))

        val myQuery : String = "hola"

        Mockito.`when`(serviceApi.searchItems("perro"))
            .then {
                    invocation ->
                invocation.getArgument<(Resource<Base>) -> Unit>(1).invoke(resp)
            }

        Assert.assertTrue(resp.data?.query == myQuery)

    }

}