package com.ilmiddin1701.restapi_get_post_put_delete.models

data class PostRequestTodo(
    val bajarildi: Boolean,
    val batafsil: String,
    val oxirgi_muddat: String,
    val sarlavha: String,
    val zarurlik: String
)
