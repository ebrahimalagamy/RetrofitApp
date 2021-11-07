package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

// First step to create interface for api and insert all fun we want it
// Second to convert JSON file to kotlin data class

interface TodoApi {
    // to retrieve the data from api
    // there are four main types of requests {getRequest, postRequest, PutRequest, deleteRequest}
    // getRequest    -> retrieve some data ,get some data
    // postRequest   -> post some data from your device to remote server
    // putRequest    -> update data on the server
    // deleteRequest -> to delete data

    // create fun to retrieve list of data
    // this string in () from url
    // we want execute this request in coroutine
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
}