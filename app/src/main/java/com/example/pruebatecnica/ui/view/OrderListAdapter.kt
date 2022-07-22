package com.example.pruebatecnica.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.RecyclerviewItemBinding
import com.example.pruebatecnica.domain.model.Order
import kotlin.coroutines.coroutineContext
import androidx.core.view.MotionEventCompat
import com.example.pruebatecnica.ui.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import android.view.MotionEvent as MotionEvent

class OrderListAdapter(val onItemClicked: (Order) -> Unit):
    ListAdapter<Order, OrderListAdapter.OrderViewHolder>(OrdersCompator()),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener{
    private lateinit var mDetector: GestureDetectorCompat
    lateinit var orderDouble: Order

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        mDetector = GestureDetectorCompat( holder.itemView.context, this)
        mDetector.setOnDoubleTapListener(this)
        holder.itemView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                orderDouble = current
                return if (mDetector.onTouchEvent(event)) {
                    true
                } else {
                    false
                }
            }
        })
        holder
    }

    class OrderViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        private var binding = RecyclerviewItemBinding.bind(itemView)

        fun bind(order: Order?) {
            var typeOrder = ""

            if (order?.OrderType == 1){
                binding.root.setBackgroundColor(Color.parseColor("#ada4a4"))
                typeOrder = "Dine In"
            }
            else if(order?.OrderType == 2){
                binding.root.setBackgroundColor(Color.parseColor("#7dcc3d"))
                typeOrder = "To Go"
            }
            else if (order?.OrderType == 3){
                binding.root.setBackgroundColor(Color.parseColor("#ffae00"))
                typeOrder = "Pick Up"
            }
            else if (order?.OrderType == 4){
                binding.root.setBackgroundColor(Color.parseColor("#009dff"))
                typeOrder = "Delivery"
            }
            val text: String? = "Order #:" + order?.OrderId + " User:" + order?.Username +
                    " Total:$" + order?.SubTotal + " Ticket #:" + order?.TicketNumber
            val textDate: String? = "Order Time: " + order?.OrderDateTime
            val stringText = text + "\n" + textDate + "\n" + typeOrder + "."
            binding.textView.text = stringText
        }
        companion object {
            fun create(parent: ViewGroup): OrderViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return OrderViewHolder(view)
            }
        }
    }

    class OrdersCompator: DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.OrderId == newItem.OrderId
        }
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        val item = orderDouble
        onItemClicked(item)
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }
}