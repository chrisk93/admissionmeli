package com.example.mercadolibreprueba.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.databinding.LayoutItemDescBinding
import com.example.mercadolibreprueba.model.search.Attributes
import java.util.Collections.emptyList

class AdapterDesc  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Attributes> = emptyList()

    fun setData(items: List<Attributes>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_desc, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private  val binding = LayoutItemDescBinding.bind(itemView)

        fun bind(item: Attributes) = with(binding) {
            textTitle.text = itemView.context.getString(R.string.attribute,item.name)
            textDesc.text = item.value_name
        }

    }
}