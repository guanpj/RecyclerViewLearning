package com.me.guanpj.recyclerviewlearning.multitype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.me.guanpj.recyclerviewlearning.R

class MultiTypeActivity : AppCompatActivity() {

    private val adapter = MultiTypeAdapter()
    private val items = ArrayList<Any>()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.register(FooViewBinder())
        adapter.register(RichItemDelegate())
        adapter.register(StringViewDelegate())

        recyclerView.adapter = adapter

        val fooItem = FooItem("hhh")
        val richItem = RichItem("rich", R.mipmap.ic_launcher)

        for (i in 1..5) {
            items.add(fooItem)
            items.add(richItem)
        }

        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}