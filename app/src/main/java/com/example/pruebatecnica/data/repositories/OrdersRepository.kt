package com.example.pruebatecnica.data.repositories

import com.example.pruebatecnica.data.network.OrderApiClient
import com.example.pruebatecnica.data.network.OrderService
import com.example.pruebatecnica.data.network.model.OrderModel
import com.example.pruebatecnica.domain.model.Order
import com.example.pruebatecnica.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val service: OrderService
) {
    fun GetOrdersFromApi(): Flow<List<Order>>{
        try {
            val response: Flow<List<OrderModel>> = service.GetOrders()
            return response.map { it -> it.map { it.toDomain() } }
        }
        catch (e: Exception){
            return emptyFlow()
        }

    }
    suspend fun UpdateOrder(orderModel: OrderModel): Order?{
        try {
            val response = service.UpdateOrder(orderModel)
            return response?.toDomain()

        }
        catch (e: Exception){
            return null
        }
    }
}