@file:Suppress("unused")

package me.yifeiyuan.flap.dsl

import androidx.recyclerview.widget.RecyclerView
import me.yifeiyuan.flap.Component
import me.yifeiyuan.flap.hook.AdapterHook

/**
 * Created by 程序亦非猿 on 2022/9/5.
 *
 * @since 3.1.0
 */

/**
 * @since 3.1.0
 */
fun adapterHook(block: DslAdapterHook.() -> Unit): AdapterHook {
    return DslAdapterHook().apply(block)
}

/**
 * @since 3.1.0
 */
class DslAdapterHook : AdapterHook {

    private var onCreateViewHolderStart: ((adapter: RecyclerView.Adapter<*>, viewType: Int) -> Unit)? = null
    private var onCreateViewHolderEnd: ((adapter: RecyclerView.Adapter<*>, viewType: Int, component: Component<*>) -> Unit)? = null

    private var onViewAttachedToWindow: ((adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit)? = null
    private var onViewDetachedFromWindow: ((adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit)? = null

    private var onBindViewHolderStart: ((adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit)? = null
    private var onBindViewHolderEnd: ((adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit)? = null

    fun onCreateViewHolderStart(block: (adapter: RecyclerView.Adapter<*>, viewType: Int) -> Unit) {
        onCreateViewHolderStart = block
    }

    fun onCreateViewHolderEnd(block: (adapter: RecyclerView.Adapter<*>, viewType: Int, component: Component<*>) -> Unit) {
        onCreateViewHolderEnd = block
    }

    fun onViewAttachedToWindow(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit) {
        onViewAttachedToWindow = block
    }

    fun onViewDetachedFromWindow(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit) {
        onViewDetachedFromWindow = block
    }

    fun onBindViewHolderStart(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit) {
        onBindViewHolderStart = block
    }

    fun onBindViewHolderEnd(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit) {
        onBindViewHolderEnd = block
    }

    override fun onCreateViewHolderStart(adapter: RecyclerView.Adapter<*>, viewType: Int) {
        onCreateViewHolderStart?.invoke(adapter, viewType)
    }

    override fun onCreateViewHolderEnd(adapter: RecyclerView.Adapter<*>, viewType: Int, component: Component<*>) {
        onCreateViewHolderEnd?.invoke(adapter, viewType, component)
    }

    override fun onBindViewHolderStart(adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) {
        onBindViewHolderStart?.invoke(adapter, component, data, position, payloads)
    }

    override fun onBindViewHolderEnd(adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) {
        onBindViewHolderEnd?.invoke(adapter, component, data, position, payloads)
    }

    override fun onViewAttachedToWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {
        onViewAttachedToWindow?.invoke(adapter, component)
    }

    override fun onViewDetachedFromWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {
        onViewDetachedFromWindow?.invoke(adapter, component)
    }
}