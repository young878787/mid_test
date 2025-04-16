package com.example.midtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.midtest.R
import com.example.midtest.models.Store

class StoreAdapter(private val context: Context, private val storeList: List<Store>) : BaseAdapter() {

    override fun getCount(): Int = storeList.size

    override fun getItem(position: Int): Any = storeList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.store_item, parent, false)
        val store = storeList[position]

        view.findViewById<TextView>(R.id.tv_store_name).text = store.name
        view.findViewById<TextView>(R.id.tv_store_phone).text = store.phone

        return view
    }
}