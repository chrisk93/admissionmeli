package com.example.mercadolibreprueba.api

import android.content.Context
import com.example.mercadolibreprueba.model.controller.Resource
import com.example.mercadolibreprueba.model.controller.ResponseHandler
import com.example.mercadolibreprueba.model.search.Base
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

class ServiceApi @Inject constructor(private val service: Service, @ApplicationContext appContext: Context) : ResponseHandler(appContext) {

    suspend fun searchItems(query: String) : Resource<Base> {
        return try {
            val response = service.getSearchItems(query = query)
            return handleSuccess(response)
        } catch (e: Exception) {
            handleException(e)
        }
    }

}