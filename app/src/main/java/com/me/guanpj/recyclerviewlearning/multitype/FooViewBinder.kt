package com.me.guanpj.recyclerviewlearning.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.me.guanpj.recyclerviewlearning.R

class FooViewBinder : ItemViewBinder<FooItem, FooViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_data_type1, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: FooItem) {
        holder.title.text = item.value
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }
}
