package com.example.pruebatecnica.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentDetailOrderBinding
import com.example.pruebatecnica.domain.model.Order
import com.example.pruebatecnica.ui.viewmodel.OrdersViewModel

class DetailOrderFragment : Fragment() {
    private val ordersViewModel: OrdersViewModel by activityViewModels()
    private val navigationArgs: DetailOrderFragmentArgs by navArgs()
    lateinit var order: Order

    private var _binding: FragmentDetailOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun bind(order: Order){
        binding.apply {
            orderNumber.text = "Order #: " + order.OrderId.toString()
            orderUser.text = "User: " + order.Username
            orderTotal.text = "Total: $" + order.SubTotal.toString()
            orderTicket.text = "Ticket #: " + order.TicketNumber.toString()
            orderTime.text = "Order Time: " + order.OrderDateTime
            editOrder.setOnClickListener{ EditOrder()}
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orderNumber = navigationArgs.orderNumber
        ordersViewModel.GetOrder(orderNumber).observe(this.viewLifecycleOwner){ selectedOder ->
            order = selectedOder as Order
            bind(order)
        }
    }

    private fun EditOrder(){
        val action = DetailOrderFragmentDirections.actionOrderDetailFragmentToOrderUpdateFragment(order.OrderId)
        this.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}