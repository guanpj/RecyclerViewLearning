package com.me.guanpj.recyclerviewlearning.multitype

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.drakeet.multitype.ViewDelegate

class RichItemDelegate : ViewDelegate<RichItem, RichView>() {

    override fun onCreateView(context: Context): RichView {
        return RichView(context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onBindView(view: RichView, item: RichItem) {
        view.imageView.setImageResource(item.imageResId)
        view.textView.text = """
      |${item.text}
      |layoutPosition: ${view.layoutPosition} 
      |absoluteAdapterPosition: ${view.absoluteAdapterPosition} 
      |bindingAdapterPosition: ${view.bindingAdapterPosition} 
    """.trimMargin()
    }
}