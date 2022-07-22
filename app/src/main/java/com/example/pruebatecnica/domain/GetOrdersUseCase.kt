package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.repositories.OrdersRepository
import com.example.pruebatecnica.domain.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    fun GetOrderType(orderType: Int): Flow<List<Order>>{
        var orders: Flow<List<Order>> = repository.GetOrdersFromApi()
        if (orderType > 0) orders = orders.map { it -> it.filter { order -> order.OrderType == orderType } }
        return orders
    }

    fun GetOrder(id: Int): Flow<Order?>{
        val order: Flow<Order?> = repository.GetOrdersFromApi().map {  it -> it.find { order -> order.OrderId == id }}
        return  order
    }
}