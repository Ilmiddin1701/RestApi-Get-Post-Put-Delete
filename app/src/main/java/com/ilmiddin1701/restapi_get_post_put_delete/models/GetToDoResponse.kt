package com.ilmiddin1701.restapi_get_post_put_delete.models

import java.io.Serializable

data class GetToDoResponse(
    val bajarildi: Boolean,
    val batafsil: String,
    val id: Int,
    val oxirgi_muddat: String,
    val sana: String,
    val sarlavha: String,
    val zarurlik: String
): Serializable