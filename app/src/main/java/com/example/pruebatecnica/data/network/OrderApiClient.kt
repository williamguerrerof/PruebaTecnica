package com.example.pruebatecnica.data.network

import com.example.pruebatecnica.data.network.model.OrderModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface OrderApiClient {
    @GET("/api/test/getOrders")
    suspend fun GetAllOrders(): Response<List<OrderModel>>

    @PUT("/api/test/updateOrder")
    fun UpdateOrder(@Body orderModel: OrderModel): Call<OrderModel>
}