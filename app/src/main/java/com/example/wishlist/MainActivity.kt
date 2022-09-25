package com.example.wishlist

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    //var items = arrayListOf<Items>()
    lateinit var items : MutableList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = ArrayList<Items>()
        // lookup the recyclerview in the activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRv)
        // Create adapter passing the list of items
        val adapter = listAdapter(items)
        itemsRv.adapter = adapter
        itemsRv.layoutManager = LinearLayoutManager(this)

        val button = findViewById<Button>(R.id.button)
        val itemName  = findViewById<TextView>(R.id.itemName)
        val itemPrice = findViewById<TextView>(R.id.itemPrice)
        val itemURL = findViewById<TextView>(R.id.itemURL)

        //itemsRv.layoutManager = LinearLayoutManager(this)
        button.setOnClickListener {
            val item = Items(itemName.text.toString(), itemPrice.text.toString(), itemURL.text.toString())
            items.add(item)
            adapter.notifyDataSetChanged()

            itemName.text =""
            itemPrice.text = ""
            itemURL.text = " "


        }
    }
}