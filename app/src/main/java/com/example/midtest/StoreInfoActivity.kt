package com.example.midtest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.midtest.R
import com.example.midtest.adapters.StoreAdapter
import com.example.midtest.models.Store

class StoreInfoActivity : AppCompatActivity() {

    private val storeList = mutableListOf<Store>()
    private lateinit var adapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_info)

        val lvStoreList: ListView = findViewById(R.id.lv_store_list)
        val btnAddStore: Button = findViewById(R.id.btn_add_store)

        adapter = StoreAdapter(this, storeList)
        lvStoreList.adapter = adapter

        // 點擊店家時，撥打電話
        lvStoreList.setOnItemClickListener { _, _, position, _ ->
            val store = storeList[position]
            val phoneUri = Uri.parse("tel:${store.phone}")
            val dialIntent = Intent(Intent.ACTION_DIAL, phoneUri)
            try {
                startActivity(dialIntent) // 啟動撥號頁面
            } catch (e: Exception) {
                Toast.makeText(this, "無法撥號: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // 長按店家時，更新店家資訊
        lvStoreList.setOnItemLongClickListener { _, _, position, _ ->
            val store = storeList[position]
            showUpdateStoreDialog(store)
            true
        }

        // 點擊新增店家按鈕
        btnAddStore.setOnClickListener {
            showAddStoreDialog()
        }
    }

    private fun showAddStoreDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("新增店家")
            .setView(dialogView)
            .setPositiveButton("新增") { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.et_store_name).text.toString()
                val phone = dialogView.findViewById<EditText>(R.id.et_store_phone).text.toString()
                val address = dialogView.findViewById<EditText>(R.id.et_store_address).text.toString()
                storeList.add(Store(name, phone, address))
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("取消", null)
            .create()
        dialog.show()
    }

    private fun showUpdateStoreDialog(store: Store) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val etName = dialogView.findViewById<EditText>(R.id.et_store_name)
        val etPhone = dialogView.findViewById<EditText>(R.id.et_store_phone)
        val etAddress = dialogView.findViewById<EditText>(R.id.et_store_address)

        etName.setText(store.name)
        etPhone.setText(store.phone)
        etAddress.setText(store.address)

        val dialog = AlertDialog.Builder(this)
            .setTitle("更新店家")
            .setView(dialogView)
            .setPositiveButton("更新") { _, _ ->
                store.name = etName.text.toString()
                store.phone = etPhone.text.toString()
                store.address = etAddress.text.toString()
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("取消", null)
            .create()
        dialog.show()
    }
}