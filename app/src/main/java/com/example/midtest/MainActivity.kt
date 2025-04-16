package com.example.midtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvOrderSummary: TextView
    private lateinit var pizzaOrderLauncher: ActivityResultLauncher<Intent>
    private lateinit var sideOrderLauncher: ActivityResultLauncher<Intent>
    private lateinit var storeInfoLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOrderSummary = findViewById(R.id.tv_order_summary)
        val btnOrderPizza: Button = findViewById(R.id.btn_order_pizza)
        val btnOrderSide: Button = findViewById(R.id.btn_order_side)
        val btnStoreInfo: Button = findViewById(R.id.btn_store_info)

        // 初始化 Pizza 的 ActivityResultLauncher
        pizzaOrderLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val pizzaOrder = data?.getStringExtra("pizzaOrder") ?: "尚未點餐"
                tvOrderSummary.text = "目前點餐內容: $pizzaOrder"
            }
        }

        // 初始化 Side 的 ActivityResultLauncher
        sideOrderLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val sideOrder = data?.getStringExtra("sideOrder") ?: "尚未選擇副餐"
                tvOrderSummary.text = "${tvOrderSummary.text}\n副餐: $sideOrder"
            }
        }

        // 初始化 StoreInfo 的 ActivityResultLauncher
        storeInfoLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val storeInfo = data?.getStringExtra("storeName") ?: "尚未選擇店家"
                tvOrderSummary.text = "${tvOrderSummary.text}\n店家: $storeInfo"
            }
        }

        // 點餐按鈕點擊事件
        btnOrderPizza.setOnClickListener {
            val intent = Intent(this, OrderPizzaActivity::class.java)
            pizzaOrderLauncher.launch(intent)
        }

        // 點副餐按鈕點擊事件
        btnOrderSide.setOnClickListener {
            val intent = Intent(this, OrderSideActivity::class.java)
            sideOrderLauncher.launch(intent)
        }

        // 店家資訊按鈕點擊事件
        btnStoreInfo.setOnClickListener {
            val intent = Intent(this, StoreInfoActivity::class.java)
            storeInfoLauncher.launch(intent)
        }
    }
}