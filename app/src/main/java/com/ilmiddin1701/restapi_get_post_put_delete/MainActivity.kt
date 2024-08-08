package com.ilmiddin1701.restapi_get_post_put_delete

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.ilmiddin1701.restapi_get_post_put_delete.VeiwModel.MyViewModel
import com.ilmiddin1701.restapi_get_post_put_delete.adapters.RvAdapter
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityMainBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse
import com.ilmiddin1701.restapi_get_post_put_delete.retrofit.ApiClient
import com.ilmiddin1701.restapi_get_post_put_delete.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RvAdapter.RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

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
        myViewModel.getAllTodo().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    rvAdapter = RvAdapter(this, it.data as ArrayList)
                    binding.rv.adapter = rvAdapter
                }
            }
        }
    }

    override fun moreClick(getToDoResponse: GetToDoResponse, position: Int, image: ImageView) {
        val menu = PopupMenu(this, image)
        menu.inflate(R.menu.my_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_delete -> {
                    ApiClient.getApiService().deleteTodo(getToDoResponse.id)
                        .enqueue(object : Callback<Any> {
                            override fun onResponse(p0: Call<Any>, p1: Response<Any>) {
                                if (p1.isSuccessful) {
                                    Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
                                    onResume()
                                }
                            }
                            override fun onFailure(p0: Call<Any>, p1: Throwable) {
                                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                            }
                        })
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