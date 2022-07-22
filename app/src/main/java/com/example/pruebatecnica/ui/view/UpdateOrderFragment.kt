package com.example.pruebatecnica.ui.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.is24HourFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pruebatecnica.databinding.FragmentDetailOrderBinding
import com.example.pruebatecnica.databinding.FragmentUpdateOrderBinding
import com.example.pruebatecnica.domain.model.Order
import com.example.pruebatecnica.ui.viewmodel.OrdersViewModel
import java.util.*

class UpdateOrderFragment: Fragment() {
    private val ordersViewModel: OrdersViewModel by activityViewModels()
    private val navigationArgs: DetailOrderFragmentArgs by navArgs()
    lateinit var order: Order

    private var _binding: FragmentUpdateOrderBinding? = null
    private val binding get() = _binding!!

    private fun isEntryValid(): Boolean {
        return ordersViewModel.isEntryValid(
            binding.orderUser.text.toString(),
            binding.orderTotal.text.toString(),
            binding.orderTime.text.toString(),
        )
    }

    private fun bind(order: Order){
        binding.apply {
            orderNumber.text = "Order #: " + order.OrderId.toString()
            orderUser.setText(order.Username, TextView.BufferType.SPANNABLE)
            orderTotal.setText(order.SubTotal.toString(), TextView.BufferType.SPANNABLE)
            orderTicket.text = "Ticket #: " + order.TicketNumber.toString()
            orderTime.setText(order.OrderDateTime, TextView.BufferType.SPANNABLE)

            cancelOrder.setOnClickListener{ CancelOrderEdit() }
            saveOrder.setOnClickListener{ UpdateOrder() }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orderNumber = navigationArgs.orderNumber
        ordersViewModel.GetOrder(orderNumber).observe(this.viewLifecycleOwner){ selectedOder ->
            order = selectedOder as Order
            bind(order)
        }

        binding.orderTime.setOnClickListener{
            showDatePickerDialog()
        }
    }

    private fun UpdateOrder(){
        if(isEntryValid()){
            var id: Int = navigationArgs.orderNumber
            var orderUpdate = order

            orderUpdate.Username = binding.orderUser.text.toString()
            orderUpdate.SubTotal = binding.orderTotal.text.toString().toDouble()
            orderUpdate.OrderDateTime = binding.orderTime.text.toString()
            val response = ordersViewModel.UpdateOrder(orderUpdate)
            val action = UpdateOrderFragmentDirections.actionOrderUpdateFragmentToOrdersListFragment()
            this.findNavController().navigate(action)

        }
    }

    private fun showDatePickerDialog() {
        val newFragmentDate = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            var c = Calendar.getInstance()
            var hour = c.get(Calendar.HOUR_OF_DAY)
            var minute = c.get(Calendar.MINUTE)
            var a: String = ""
            if(hour <= 12) a ="AM"
            else{
                a = "PM"
                hour = hour - 12
            }
            var minuteFormat = ""
            minuteFormat = if(minute < 10) "0" + minute
            else minute.toString()

            val selectedDate = (month + 1).toString()  + "/" + day + "/" + year +
                    " " + hour + ":" + minuteFormat + ":" + "00" + " " + a
            binding.orderTime.setText(selectedDate)
        })

        /*val newFragmentTime = TimePickerFragment.newInstance(TimePickerDialog.OnTimeSetListener {_, hour, minute ->
            val selectedTime = hour.toString() + ":" + ":" + "00"
        })

        newFragmentTime.show(requireActivity().supportFragmentManager, "timePicker")*/
        newFragmentDate.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun CancelOrderEdit(){
        val action = UpdateOrderFragmentDirections.actionOrderUpdateFragmentToOrdersListFragment()
        this.findNavController().navigate(action)
    }

    class DatePickerFragment : DialogFragment() {

        private var listener: DatePickerDialog.OnDateSetListener? = null

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(this.requireContext(), listener, year, month, day)
        }

        companion object {
            fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
                val fragment = DatePickerFragment()
                fragment.listener = listener
                return fragment
            }
        }

    }

    class TimePickerFragment : DialogFragment() {
        private var listener: TimePickerDialog.OnTimeSetListener? = null
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current time as the default values for the picker
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // Create a new instance of TimePickerDialog and return it
            return TimePickerDialog(this.requireContext(), listener, hour, minute, false)
        }

        companion object{
            fun newInstance(listener: TimePickerDialog.OnTimeSetListener): TimePickerFragment {
                val fragment = TimePickerFragment()
                fragment.listener = listener
                return fragment
            }
        }
        /*override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
            // Do something with the time chosen by the user
        }*/
    }

}
