package com.example.pruebatecnica.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.FtsOptions
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityMainBinding
import com.example.pruebatecnica.databinding.ActivityRecallBinding
import com.example.pruebatecnica.databinding.FragmentOrdersBinding
import com.example.pruebatecnica.domain.model.Order
import com.example.pruebatecnica.ui.viewmodel.OrdersViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A fragment representing a list of Items.
 */
class OrdersFragment : Fragment() {

    private var columnCount = 1
    private val ordersViewModel: OrdersViewModel by activityViewModels()
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        binding.textViewDateNow.text = formatted

        initRecycleView(ordersViewModel.GetOrderType(0))
        binding.buttonAll.setOnClickListener{
            initRecycleView(ordersViewModel.GetOrderType(0))
        }

        binding.buttonDinein.setOnClickListener{
            initRecycleView(ordersViewModel.GetOrderType(1))
        }
        binding.buttonTogo.setOnClickListener{
            initRecycleView(ordersViewModel.GetOrderType(2))
        }
        binding.buttonPickup.setOnClickListener{
            initRecycleView(ordersViewModel.GetOrderType(3))
        }
        binding.buttonDelivery.setOnClickListener{
            initRecycleView(ordersViewModel.GetOrderType(4))
        }
    }

    private fun initRecycleView(orders: LiveData<List<Order>>){
        val adapter = OrderListAdapter{
            val action =
                OrdersFragmentDirections.actionOrdersListFragmentToOrderDetailFragment(it.OrderId)
            this.findNavController().navigate(action)
        }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        ordersViewModel.onCreate()
        orders.observe(this.viewLifecycleOwner) { orders ->
            orders?.let { adapter.submitList(it) }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            OrdersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}