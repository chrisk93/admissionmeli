package com.example.mercadolibreprueba.model.search

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Seller (

	@SerializedName("id") val id : Int,
	@SerializedName("power_seller_status") val power_seller_status : String,
	@SerializedName("car_dealer") val car_dealer : Boolean,
	@SerializedName("real_estate_agency") val real_estate_agency : Boolean,
	@SerializedName("tags") val tags : List<String>
) : Serializable