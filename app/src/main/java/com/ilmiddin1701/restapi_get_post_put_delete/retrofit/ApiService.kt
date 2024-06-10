package com.ilmiddin1701.restapi_get_post_put_delete.retrofit

import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequesToDo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("plan")
    fun getAllToDo(): Call<List<GetToDoResponse>>

    @POST("plan/")
    fun addToDo(@Body postRequesToDo: PostRequesToDo): Call<GetToDoResponse>
}