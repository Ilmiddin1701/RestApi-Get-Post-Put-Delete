package com.ilmiddin1701.restapi_get_post_put_delete

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.ilmiddin1701.restapi_get_post_put_delete.VeiwModel.MyViewModel
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityAddBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequestTodo
import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiClient
import com.ilmiddin1701.restapi_get_post_put_delete.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private lateinit var getToDoResponse: GetToDoResponse
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val key = intent.getIntExtra("key", 1)
        onBackPressedDispatcher.addCallback(onBackPressedCallBack)
        if (key == 1) {
            postTodo()
        } else {
            getToDoResponse = intent.getSerializableExtra("keyTodo") as GetToDoResponse
            binding.mySwitch.visibility = View.VISIBLE
            editTodo()
        }
    }

    private fun postTodo() {
        binding.apply {
            btnSave.setOnClickListener {
                val postRequestToDo = PostRequestTodo(
                    false,
                    edtBatafsil.text.toString().trim(),
                    edtOxirgiMuddat.text.toString().trim(),
                    edtSarlavha.text.toString().trim(),
                    edtZarurlik.text.toString().trim()
                )
                myViewModel.addTodo(postRequestToDo).observe(this@AddActivity) {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            btnSave.isEnabled = false
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            btnSave.isEnabled = true
                            Toast.makeText(this@AddActivity, it.message, Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS -> {
                            Toast.makeText(this@AddActivity, "Saved", Toast.LENGTH_SHORT).show()
                            finish()
                            startActivity(Intent(this@AddActivity, MainActivity::class.java))
                        }
                    }
                }
            }
        }
    }

    private fun editTodo() {
        binding.apply {
            edtSarlavha.setText(getToDoResponse.sarlavha)
            edtBatafsil.setText(getToDoResponse.batafsil)
            edtOxirgiMuddat.setText(getToDoResponse.oxirgi_muddat)
            edtZarurlik.setText(getToDoResponse.zarurlik)
            mySwitch.isChecked = getToDoResponse.bajarildi
            btnSave.setOnClickListener {
                ApiClient.getApiService().updateTodo(getToDoResponse.id,
                    PostRequestTodo(
                        mySwitch.isChecked,
                        edtBatafsil.text.toString(),
                        edtOxirgiMuddat.text.toString(),
                        edtSarlavha.text.toString(),
                        edtZarurlik.text.toString()
                    )
                ).enqueue(object : Callback<GetToDoResponse>{
                    override fun onResponse(p0: Call<GetToDoResponse>, p1: Response<GetToDoResponse>) {
                        Toast.makeText(this@AddActivity, "Edited", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this@AddActivity, MainActivity::class.java))
                    }
                    override fun onFailure(p0: Call<GetToDoResponse>, p1: Throwable) {
                        Toast.makeText(this@AddActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private val onBackPressedCallBack = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finish()
            startActivity(Intent(this@AddActivity, MainActivity::class.java))
        }
    }
}
