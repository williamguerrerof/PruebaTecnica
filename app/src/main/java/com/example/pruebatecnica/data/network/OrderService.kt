package com.example.pruebatecnica.data.network

import com.example.pruebatecnica.data.network.model.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.Optional.empty
import javax.inject.Inject

class OrderService @Inject constructor(
    private val apiClient: OrderApiClient
) {
    fun GetOrders(): Flow<List<OrderModel>>{
        try {
            val allOrders = flow {
                val response = apiClient.GetAllOrders()
                if (response.isSuccessful){
                    emit(response.body())
                }
                else{
                    emit(emptyList())
                }
            }
            return allOrders as Flow<List<OrderModel>>

        }
        catch (e: Exception){
            return emptyFlow()
        }

    }

    suspend fun UpdateOrder(orderModel: OrderModel): OrderModel?{
        try {
            return withContext(Dispatchers.IO){
                val response = apiClient.UpdateOrder(orderModel).clone().execute()
                println(response.message())
                response.body() ?: null
            }
        }
        catch (e: Exception){
            return null
        }

    }
}