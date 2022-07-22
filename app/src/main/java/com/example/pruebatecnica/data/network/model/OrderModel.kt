package com.example.pruebatecnica.data.network.model

import com.example.pruebatecnica.domain.model.Order
import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("orderId") val OrderId: Int,
    @SerializedName("username") val Username: String,
    @SerializedName("subTotal") val SubTotal: Double,
    @SerializedName("ticketNumber") val TicketNumber: Int,
    @SerializedName("orderDateTime") val OrderDateTime: String,
    @SerializedName("orderType") val OrderType: Int
    ) {
}

fun Order.toApi() = OrderModel(
    OrderId = OrderId,
    Username = Username,
    SubTotal = SubTotal,
    TicketNumber = TicketNumber,
    OrderDateTime = OrderDateTime,
    OrderType = OrderType
)