@file:Suppress("unused")

package me.yifeiyuan.flap.ext

import androidx.recyclerview.widget.RecyclerView
import me.yifeiyuan.flap.Component
import me.yifeiyuan.flap.FlapAdapter
import me.yifeiyuan.flap.hook.AdapterHook

/**
 * 一些扩展函数
 *
 * Created by 程序亦非猿 on 2022/7/29.
 * @since 3.0.0
 */

/**
 * 在 onCreateViewHolder 之前回调
 * @see doOnCreateViewHolderEnd
 */
fun FlapAdapter.doOnCreateViewHolderStart(block: (adapter: RecyclerView.Adapter<*>, viewType: Int) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onCreateViewHolderStart(adapter: RecyclerView.Adapter<*>, viewType: Int) {
            block.invoke(adapter, viewType)
        }
    })
}

/**
 * 在 onCreateViewHolder 之后回调
 * @see doOnCreateViewHolderStart
 */
fun FlapAdapter.doOnCreateViewHolderEnd(block: (adapter: RecyclerView.Adapter<*>, viewType: Int, component: Component<*>) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onCreateViewHolderEnd(adapter: RecyclerView.Adapter<*>, viewType: Int, component: Component<*>) {
            block.invoke(adapter, viewType, component)
        }
    })
}

/**
 * 在 onBindViewHolder 之前回调
 * @see doOnBindViewHolderEnd
 */
fun FlapAdapter.doOnBindViewHolderStart(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onBindViewHolderStart(adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) {
            block.invoke(adapter, component, data, position, payloads)
        }
    })
}

/**
 * 在 onBindViewHolder 之后回调
 * @see doOnBindViewHolderStart
 */
fun FlapAdapter.doOnBindViewHolderEnd(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onBindViewHolderEnd(adapter: RecyclerView.Adapter<*>, component: Component<*>, data: Any, position: Int, payloads: MutableList<Any>) {
            block.invoke(adapter, component, data, position, payloads)
        }
    })
}

/**
 * @see doOnViewAttachedFromWindow
 */
fun FlapAdapter.doOnViewDetachedFromWindow(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onViewDetachedFromWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {
            block.invoke(adapter, component)
        }
    })
}

/**
 * @see doOnViewDetachedFromWindow
 */
fun FlapAdapter.doOnViewAttachedFromWindow(block: (adapter: RecyclerView.Adapter<*>, component: Component<*>) -> Unit) {
    registerAdapterHook(object : AdapterHook {
        override fun onViewAttachedToWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {
            block.invoke(adapter, component)
        }
    })
}

inline fun <reified T> Any?.ifIs(block: T.() -> Unit) {
    if (this is T) {
        block.invoke(this)
    }
}

typealias UnitBlock = () -> Unit
typealias BooleanBlock = () -> Boolean
typealias ResultBlock<T> = () -> T
typealias NullableResultBlock<T> = () -> T?