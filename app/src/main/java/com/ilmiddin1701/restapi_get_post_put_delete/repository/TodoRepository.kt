package com.ilmiddin1701.restapi_get_post_put_delete.repository

import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiService

class TodoRepository(val apiService: ApiService) {
    suspend fun getAllTodo() = apiService.getAllToDo()
}