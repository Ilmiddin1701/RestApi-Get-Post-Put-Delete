package com.ilmiddin1701.restapi_get_post_put_delete.retrofit

import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequestTodo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("plan")
    suspend fun getAllToDo(): List<GetToDoResponse>

    @POST("plan/")
    suspend fun addToDo(@Body postRequestToDo: PostRequestTodo): GetToDoResponse

    @DELETE("plan/{id}/")
    fun deleteTodo(@Path("id") id: Int): Call<Any>

    @PUT("plan/{id}/")
    fun updateTodo(@Path("id") id: Int, @Body postRequestToDo: PostRequestTodo): Call<GetToDoResponse>
}