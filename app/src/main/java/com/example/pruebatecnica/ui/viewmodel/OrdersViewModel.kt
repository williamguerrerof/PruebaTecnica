package com.example.pruebatecnica.ui.viewmodel

import androidx.lifecycle.*
import com.example.pruebatecnica.domain.GetOrdersUseCase
import com.example.pruebatecnica.domain.UpdateOrderUseCase
import com.example.pruebatecnica.domain.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase
): ViewModel() {

    fun GetOrderType(orderType: Int): LiveData<List<Order>>{
        return getOrdersUseCase.GetOrderType(orderType).asLiveData()
    }
    fun GetOrder(id: Int): LiveData<Order?> {
        return getOrdersUseCase.GetOrder(id).asLiveData()
    }
    fun onCreate(){
        viewModelScope.launch {

        }
    }
    fun UpdateOrder(order: Order): Order?{
        var ordert: Order? = null
        viewModelScope.launch {
             ordert = updateOrderUseCase.UpdateOrder(order)
        }
        return ordert
    }
    fun isEntryValid(userName: String, subTotal: String, orderDatetime: String): Boolean {
        if (userName.isBlank() || subTotal.isBlank() || orderDatetime.isBlank()) {
            return false
        }
        return true
    }
}