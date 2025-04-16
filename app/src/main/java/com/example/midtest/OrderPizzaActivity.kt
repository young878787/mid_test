package com.example.midtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class OrderPizzaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_pizza)

        val rgPizzaFlavors: RadioGroup = findViewById(R.id.rg_pizza_flavors)
        val btnConfirmPizza: Button = findViewById(R.id.btn_confirm_pizza)

        btnConfirmPizza.setOnClickListener {
            val selectedFlavor = when (rgPizzaFlavors.checkedRadioButtonId) {
                R.id.rb_pizza_margherita -> "瑪格麗特"
                R.id.rb_pizza_pepperoni -> "義式臘腸"
                R.id.rb_pizza_vegetarian -> "素食"
                else -> "未選擇"
            }
            val resultIntent = Intent().apply {
                putExtra("pizzaOrder", selectedFlavor)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}