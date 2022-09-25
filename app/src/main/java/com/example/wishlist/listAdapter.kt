package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.util.Log

class listAdapter (private val items:MutableList<Items>) : RecyclerView.Adapter<listAdapter.ViewHolder>(){
    // Provide a direct reference to each of the view within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // TODO: create member variables for any view that will set
        // as you render a row
        val itemNameTv: TextView
        val  itemPriceTv: TextView
        val  itemURLTv: TextView
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view

        init {
            // TODO: store each of the layout's views into
            // the public final member variables created above
            itemNameTv = itemView.findViewById(R.id.itemNameTv)
            itemPriceTv = itemView.findViewById(R.id.itemPriceTv)
            itemURLTv = itemView.findViewById(R.id.itemURLTv)
        }

    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // TODO("implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.activity_main1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.itemNameTv.text = item.itemName
        holder.itemPriceTv.text = item.itemPrice
        holder.itemURLTv.text = item.itemUrl
        holder.itemView.setOnClickListener {
            items.removeAt(position)
            this.notifyDataSetChanged()
            true
        }
        holder.itemView.setOnClickListener{
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.itemUrl))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.itemName, Toast.LENGTH_LONG).show()
            }
        }


    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return items.size

    }
}

