package com.ilmiddin1701.restapi_get_post_put_delete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

        }
    }
}