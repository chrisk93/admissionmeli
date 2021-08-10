package com.example.mercadolibreprueba.model.search

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String
)  : Serializable