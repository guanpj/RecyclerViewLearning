package com.me.guanpj.recyclerviewlearning.multitype

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate

/**
 * @author Drakeet Xu
 */
class StringViewDelegate : ItemViewDelegate<String, StringViewDelegate.ViewHolder>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.test_list_item, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: String) {}

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
