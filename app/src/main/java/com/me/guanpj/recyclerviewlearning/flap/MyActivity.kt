package com.me.guanpj.recyclerviewlearning.flap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.guanpj.recyclerviewlearning.R
import me.yifeiyuan.flap.FlapAdapter
import me.yifeiyuan.flap.dsl.adapterDelegate
import me.yifeiyuan.flap.ext.bindTextView

class MyActivity : AppCompatActivity() {

    val del = adapterDelegate<MyTextModel>(R.layout.item_my_list) {
        onBind { model ->
            bindTextView(R.id.tv_content) {
                text = model.content
            }
        }
    }

    var adapter = FlapAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        adapter.registerAdapterDelegate(del)

        val dataList = ArrayList<Any>().apply {
            add(MyTextModel("abc"))
            add(MyTextModel("java"))
            add(MyTextModel("android"))
        }

        adapter.setDataAndNotify(dataList)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


}