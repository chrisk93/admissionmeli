package com.example.mercadolibreprueba.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.databinding.LayoutListItemBinding
import com.example.mercadolibreprueba.model.search.Results
import java.util.Collections.emptyList
import javax.inject.Inject

class AdapterProducts @Inject constructor(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Results> = emptyList()

    fun setData(items: List<Results>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private  val binding = LayoutListItemBinding.bind(itemView)

        fun bind(item: Results, listener: OnItemClickListener) = with(binding) {

            textViewName.text = item.title
            textViewPrice.text = itemView.context.getString(R.string.price, item.price.toInt())
            textViewAvailable.text = itemView.context.getString(R.string.quantity, item.available_quantity)
            textViewCondition.text = itemView.context.getString(R.string.state, item.condition)

            Glide.with(itemView)
                    .load(item.thumbnail)
                    .placeholder(R.drawable.image_not)
                    .into(imageViewIcon)

            cardItem.setOnClickListener {
                listener.onItemClick(item)
            }
        }

    }
}