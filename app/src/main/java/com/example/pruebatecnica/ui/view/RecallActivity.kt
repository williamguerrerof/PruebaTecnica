package com.example.pruebatecnica.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityRecallBinding
import com.example.pruebatecnica.ui.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class RecallActivity : AppCompatActivity()/*,
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener*/{
    private lateinit var binding: ActivityRecallBinding
/*    private val ordersViewModel: OrdersViewModel by viewModels()
    private lateinit var mDetector: GestureDetectorCompat*/
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        binding.textViewDateNow.text = formatted*/
        /*initRecycleView(ordersViewModel)*/

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // Set up the action bar for use with the NavController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
/*
    private fun initRecycleView(ordersViewModel: OrdersViewModel){
        val adapter = OrderListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        ordersViewModel.onCreate()

        ordersViewModel.allOrders.observe(this) { orders ->
            orders?.let { adapter.submitList(it) }
        }

        mDetector = GestureDetectorCompat(this, this)
        mDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
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
    *//*val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val word = intent?.getSerializableExtra(DetailOrderActivity.EXTRA_REPLY) as Word
            orde.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }*//*
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Toast.makeText(
            binding.recyclerview.rootView.context,
            "double tap two",
            Toast.LENGTH_LONG
        ).show()
*//*
            val intent = Intent(binding.root.context, DetailOrderActivity::class.java)
*//*

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }*/
}