package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.network.model.toApi
import com.example.pruebatecnica.data.repositories.OrdersRepository
import com.example.pruebatecnica.domain.model.Order
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateOrderUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend fun UpdateOrder(order: Order): Order? {

        return repository.UpdateOrder(order.toApi())
    }
}