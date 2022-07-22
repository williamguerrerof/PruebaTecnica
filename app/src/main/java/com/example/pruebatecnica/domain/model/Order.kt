package com.example.pruebatecnica.domain.model

import com.example.pruebatecnica.data.network.model.OrderModel
import com.google.gson.annotations.SerializedName

data class Order(
    val OrderId: Int,
    var Username: String,
    var SubTotal: Double,
    var TicketNumber: Int,
    var OrderDateTime: String,
    var OrderType: Int
)

fun OrderModel.toDomain() = Order(
    OrderId = OrderId,
    Username = Username,
    SubTotal = SubTotal,
    TicketNumber = TicketNumber,
    OrderDateTime = OrderDateTime,
    OrderType = OrderType
)