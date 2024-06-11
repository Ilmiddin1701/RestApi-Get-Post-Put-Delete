package com.ilmiddin1701.restapi_get_post_put_delete

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityAddBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequesToDo
import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            postTodo()
        }
        onBackPressedDispatcher.addCallback(onBackPressedCallBack)
    }

    private val onBackPressedCallBack = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finish()
            startActivity(Intent(this@AddActivity, MainActivity::class.java))
        }
    }

    private fun postTodo() {
        binding.apply {
            val postRequestToDo = PostRequesToDo(
                false,
                edtBatafsil.text.toString(),
                edtOxirgiMuddat.text.toString(),
                edtSarlavha.text.toString(),
                edtZarurlik.text.toString()
            )
            ApiClient.getApiService().addToDo(postRequestToDo)
                .enqueue(object : Callback<GetToDoResponse>{
                    override fun onResponse(p0: Call<GetToDoResponse>, p1: Response<GetToDoResponse>) {
                        if (p1.isSuccessful) {
                            Toast.makeText(this@AddActivity, "Saved", Toast.LENGTH_SHORT).show()
                            finish()
                            startActivity(Intent(this@AddActivity, MainActivity::class.java))
                        }
                    }
                    override fun onFailure(p0: Call<GetToDoResponse>, p1: Throwable) {
                        Toast.makeText(this@AddActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}