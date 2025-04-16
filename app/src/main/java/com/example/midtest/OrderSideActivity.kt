package com.example.midtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class OrderSideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_side)

        val cbSalad: CheckBox = findViewById(R.id.cb_side_salad)
        val cbFries: CheckBox = findViewById(R.id.cb_side_fries)
        val cbDrink: CheckBox = findViewById(R.id.cb_side_drink)
        val btnConfirmSide: Button = findViewById(R.id.btn_confirm_side)

        btnConfirmSide.setOnClickListener {
            val sideOrder = mutableListOf<String>()
            if (cbSalad.isChecked) sideOrder.add("沙拉")
            if (cbFries.isChecked) sideOrder.add("薯條")
            if (cbDrink.isChecked) sideOrder.add("飲料")

            val intent = Intent().apply {
                putExtra("sideOrder", sideOrder.joinToString(", "))
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}