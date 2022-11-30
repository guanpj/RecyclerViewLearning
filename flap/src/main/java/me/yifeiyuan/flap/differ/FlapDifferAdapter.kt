@file:Suppress("unused")

package me.yifeiyuan.flap.differ

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import me.yifeiyuan.flap.FlapAdapter
import java.util.*

/**
 * 支持 AsyncListDiffer 更高效的刷新。
 *
 * @see IDiffer
 *
 * Created by 程序亦非猿 on 2021/9/22.
 *
 * Flap Github: <a>https://github.com/AlanCheen/Flap</a>
 * @author 程序亦非猿 [Follow me](<a> https://github.com/AlanCheen</a>)
 * @since 2020/9/22
 * @since 3.0.0
 */
@Suppress("UNCHECKED_CAST")
class FlapDifferAdapter<T : Any> : FlapAdapter {

    private val differ: AsyncListDiffer<T>

    constructor() : super() {
        differ = AsyncListDiffer(this, object : ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                if (oldItem is IDiffer) {
                    return oldItem.areItemsTheSame(newItem)
                }
                return oldItem.javaClass == newItem.javaClass
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                if (oldItem is IDiffer) {
                    return oldItem.areContentsTheSame(newItem)
                }
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: T, newItem: T): Any? {
                if (oldItem is IDiffer) {
                    return oldItem.getChangePayload(newItem)
                }
                return null
            }
        })
    }

    constructor(itemCallback: ItemCallback<T>) {
        differ = AsyncListDiffer(this, itemCallback)
    }

    constructor(config: AsyncDifferConfig<T>) {
        differ = AsyncListDiffer(AdapterListUpdateCallback(this), config)
    }

    @JvmOverloads
    fun submitList(newList: MutableList<T>, callback: Runnable? = null) {
        if (differ.currentList === newList) {
            val data: MutableList<T> = mutableListOf<T>().apply {
                addAll(newList)
            }
            differ.submitList(data, callback)
        } else {
            differ.submitList(newList, callback)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemData(position: Int): T {
        return differ.currentList[position]
    }

    @Deprecated(message = "请使用 submitList", replaceWith = ReplaceWith("submitList(newDataList)", "me.yifeiyuan.flap.differ.FlapDifferAdapter"))
    override fun setData(newDataList: MutableList<Any>) {
        val data = mutableListOf<T>()
        for (o in newDataList) {
            data.add(o as T)
        }
        submitList(data)
    }

    @Deprecated(message = "请使用 submitList", replaceWith = ReplaceWith("submitList(newDataList)", "me.yifeiyuan.flap.differ.FlapDifferAdapter"))
    override fun <T : Any> setDataAndNotify(newDataList: MutableList<T>, byNotifyDataSetChanged: Boolean) {
        super.setDataAndNotify(newDataList, byNotifyDataSetChanged)
    }

    @Deprecated(message = "请使用 submitList", replaceWith = ReplaceWith("submitList(appendDataList)", "me.yifeiyuan.flap.differ.FlapDifferAdapter"))
    override fun appendData(appendDataList: MutableList<Any>) {
        val data = ArrayList<T>()
        data.addAll(differ.currentList)
        appendDataList.forEach {
            data.add(it as T)
        }
        submitList(data)
    }

    @Deprecated(message = "请使用 appendData", replaceWith = ReplaceWith("appendData(appendDataList)", "me.yifeiyuan.flap.differ.FlapDifferAdapter"))
    override fun appendDataAndNotify(appendDataList: MutableList<Any>, byNotifyDataSetChanged: Boolean) {
        this.appendData(appendDataList)
    }

    override fun addData(dataList: List<Any>) {
        val data = ArrayList<T>()
        dataList.forEach {
            data.add(it as T)
        }
        data.addAll(differ.currentList)
        submitList(data)
    }

    @Deprecated(message = "请使用 addData", replaceWith = ReplaceWith("addData(dataList)", "me.yifeiyuan.flap.differ.FlapDifferAdapter"))
    override fun addDataAndNotify(dataList: List<Any>, byNotifyDataSetChanged: Boolean) {
        this.addData(dataList)
    }
}