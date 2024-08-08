package com.ilmiddin1701.restapi_get_post_put_delete.VeiwModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequestTodo
import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiClient
import com.ilmiddin1701.restapi_get_post_put_delete.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val liveTodo = MutableLiveData<Resource<List<GetToDoResponse>>>()
    private val apiService = ApiClient.getApiService()

    fun getAllTodo(): MutableLiveData<Resource<List<GetToDoResponse>>> {
        viewModelScope.launch {
            liveTodo.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    val list: List<GetToDoResponse> = async {
                        apiService.getAllToDo()
                    }.await()

                    liveTodo.postValue(Resource.success(list))
                }
            } catch (e: Exception) {
                liveTodo.postValue(Resource.error(e.message.toString()))
            }
        }
        return liveTodo
    }

    private val postLiveData = MutableLiveData<Resource<GetToDoResponse>>()
    fun addTodo(postRequestToDo: PostRequestTodo): MutableLiveData<Resource<GetToDoResponse>> {
        viewModelScope.launch {
            postLiveData.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    val response = async {
                        apiService.addToDo(postRequestToDo)
                    }.await()
                    postLiveData.postValue(Resource.success(response))
                }
            } catch (e: Exception) {
                postLiveData.postValue(Resource.error(e.message.toString()))
            }
        }
        return postLiveData
    }
}