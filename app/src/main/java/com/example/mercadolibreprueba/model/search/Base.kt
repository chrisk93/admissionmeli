package com.example.mercadolibreprueba.model.search

import com.google.gson.annotations.SerializedName

data class Base (

	@SerializedName("site_id") val site_id : String,
	@SerializedName("query") val query : String,
	@SerializedName("paging") val paging : Paging?,
	@SerializedName("results") val results : List<Results>
)