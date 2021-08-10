package com.example.mercadolibreprueba.model.search

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Attributes (

	@SerializedName("name") val name : String? = "",
	@SerializedName("value_id") val value_id : String? = "",
	@SerializedName("value_name") val value_name : String? = "",
	@SerializedName("attribute_group_id") val attribute_group_id : String? = "",
	@SerializedName("attribute_group_name") val attribute_group_name : String? = "",
	@SerializedName("source") val source : Long? = 0,
	@SerializedName("id") val id : String? = ""
) : Serializable