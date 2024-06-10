package com.ilmiddin1701.restapi_get_post_put_delete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityAddBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.models.PostRequesToDo

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        binding.apply {
            val getTodoResponse = intent.getSerializableExtra("keyTodo") as GetToDoResponse
            edtSarlavha.setText(getTodoResponse.sarlavha)
            edtBatafsil.setText(getTodoResponse.batafsil)
            edtOxirgiMuddat.setText(getTodoResponse.oxirgi_muddat)
            edtZarurlik.setText(getTodoResponse.zarurlik)

            binding.btnSave.setOnClickListener {

            }
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

        }
    }

    private fun editTodo() {

    }
}