package com.example.mercadolibreprueba.model.search

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Installments (

	@SerializedName("quantity") val quantity : Int? = 0,
	@SerializedName("amount") val amount : Double? = 0.0,
	@SerializedName("rate") val rate : Double? = 0.0,
	@SerializedName("currency_id") val currency_id : String? = ""
)  : Serializable