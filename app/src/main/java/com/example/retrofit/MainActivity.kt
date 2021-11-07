package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        // Make the api request in background thread
        // this coroutine will cancelled when app in the back ground
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = RetrofitInstance.api.getTodos()
            if (response.isSuccessful && response.body() != null){
                todoAdapter.todo = response.body()!!
            }else{
                Log.e(TAG, "Error: IOException ")
            }
            binding.progressBar.isVisible = false

//            } catch (e: IOException) {
//                Log.e(TAG, "Error: IOException ")
//                return@launchWhenCreated
//                binding.progressBar.isVisible = false
//
//            } catch (e: HttpException) {
//                Log.e(TAG, "Error: HttpException ")
//                return@launchWhenCreated
//                binding.progressBar.isVisible = false
//            }


        }
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}