package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class CustomAdapter(private val mList: List<ItemsViewModel>, var click: onClick) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        //holder.textView.text = itemsViewModel.text
        holder.initialize(mList[position], click)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val textView: TextView = itemView.findViewById(R.id.textView)

        fun initialize(operation: ItemsViewModel, action: onClick) {

            textView.text = operation.text

            itemView.setOnClickListener {

                action.onItemClick(operation, adapterPosition)
            }
        }



    }

    interface onClick{

        fun onItemClick(operation: ItemsViewModel, position: Int){

        }
    }


}
