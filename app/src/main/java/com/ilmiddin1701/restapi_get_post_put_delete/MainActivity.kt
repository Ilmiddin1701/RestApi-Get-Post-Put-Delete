package com.ilmiddin1701.restapi_get_post_put_delete

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ilmiddin1701.restapi_get_post_put_delete.adapters.RvAdapter
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityMainBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RvAdapter.RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        onResume()
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            intent.putExtra("key", 1)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        ApiClient.getApiService().getAllToDo()
            .enqueue(object : Callback<List<GetToDoResponse>>{
                override fun onResponse(p0: Call<List<GetToDoResponse>>, p1: Response<List<GetToDoResponse>>) {
                    if (p1.isSuccessful) {
                        rvAdapter = RvAdapter(this@MainActivity, p1.body() as ArrayList)
                        binding.rv.adapter = rvAdapter
                    }
                }
                override fun onFailure(p0: Call<List<GetToDoResponse>>, p1: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun moreClick(getToDoResponse: GetToDoResponse, position: Int, image: ImageView) {
        val menu = PopupMenu(this, image)
        menu.inflate(R.menu.my_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_delete -> {

                    onResume()
                }
                R.id.menu_edit -> {
                    val intent = Intent(this, AddActivity::class.java)
                    intent.putExtra("keyTodo", getToDoResponse)
                    intent.putExtra("key", 2)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }
        menu.show()
    }
}