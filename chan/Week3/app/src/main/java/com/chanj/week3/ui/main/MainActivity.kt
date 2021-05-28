package com.chanj.week3.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chanj.week3.common.Constants
import com.chanj.week3.data.network.MilitaryService
import com.chanj.week3.data.network.ServiceGenerator
import com.chanj.week3.databinding.ActivityMainBinding
import com.chanj.week3.repository.MilitaryRepository
import com.chanj.week3.ui.ViewModelFactory

class MainActivity : AppCompatActivity() {
    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val viewModel: MainViewModel by lazy {
        val repository = MilitaryRepository(ServiceGenerator.createService(MilitaryService::class.java, Constants.BASE_URL))
        ViewModelProvider(this, ViewModelFactory(repository))[MainViewModel::class.java]

    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.lifecycleOwner = this // this makes ViewModel observe its LiveData.
        setContentView(binding.root)

        init()
    }

    private fun init(){
        viewModel.itemList.observe(this, Observer { list ->
            Log.d("px-list", list.toString())
            val adapter = PxItemAdapter()
        })

        viewModel.toastMsg.observe(this, Observer { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })
    }
}