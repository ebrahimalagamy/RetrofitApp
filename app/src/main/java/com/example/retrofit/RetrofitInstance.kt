package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// we want create instance from api interface
// this api instance here make make our api calls

object RetrofitInstance {
    // lazy -> means it won't be initialized right away instead
    // it will be initialize when we first access this api
    val api: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            // to tell retrofit how it should take the json data and convert it in kotlin class
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)


    }
}