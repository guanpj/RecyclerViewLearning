package me.yifeiyuan.flap.hook

import androidx.recyclerview.widget.RecyclerView
import me.yifeiyuan.flap.Component
import me.yifeiyuan.flap.FlapAdapter

/**
 *
 * AdapterHook 是基于 Adapter 做的 hooks ,可以通过这些 hooks 监听一些流程，例如组件的创建流程。
 * 并方便以 AOP & 解耦的方式实现某些功能。
 *
 * 内置的一些 AdapterHook 实现：
 * @see ApmHook 一个简易的 APM 工具
 * @see LoggingHook 一个简易的调试工具
 * @see PreloadHook  实现预取检测功能，可以用做预加载或加载更多功能
 *
 * Created by 程序亦非猿 on 2021/9/22.
 *
 * Flap Github: <a>https://github.com/AlanCheen/Flap</a>
 * @author 程序亦非猿 [Follow me](<a> https://github.com/AlanCheen</a>)
 * @since 2020/9/22
 * @since 3.0.0
 */
interface AdapterHook {

    /**
     * 在创建组件前调用
     *
     * @param adapter 正在创建组件的 adapter
     * @param viewType viewType
     */
    fun onCreateViewHolderStart(adapter: RecyclerView.Adapter<*>, viewType: Int) {}

    /**
     * 在组件创建完毕后调用
     *
     * @param adapter  正在创建组件的 adapter
     * @param viewType viewType
     * @param component 被创建出来的组件
     */
    fun onCreateViewHolderEnd(
            adapter: RecyclerView.Adapter<*>,
            viewType: Int,
            component: Component<*>) {
    }

    /**
     * 在绑定组件之前调用
     *
     * @param adapter
     * @param component
     * @param position
     * @param data
     * @param payloads
     */
    fun onBindViewHolderStart(
            adapter: RecyclerView.Adapter<*>,
            component: Component<*>,
            data: Any,
            position: Int,
            payloads: MutableList<Any>) {
    }

    /**
     * 在组件绑定完毕后回调
     *
     * @param adapter
     * @param component
     * @param position
     * @param data
     * @param payloads
     */
    fun onBindViewHolderEnd(
            adapter: RecyclerView.Adapter<*>,
            component: Component<*>,
            data: Any,
            position: Int,
            payloads: MutableList<Any>) {
    }

    /**
     * @see FlapAdapter.onViewAttachedToWindow
     */
    fun onViewAttachedToWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {}

    /**
     * @see FlapAdapter.onViewDetachedFromWindow
     */
    fun onViewDetachedFromWindow(adapter: RecyclerView.Adapter<*>, component: Component<*>) {}

    /**
     * @see FlapAdapter.onAttachedToRecyclerView
     * @since 3.1.5
     */
    fun onAttachedToRecyclerView(adapter: RecyclerView.Adapter<*>, recyclerView: RecyclerView) {}

    /**
     * @see FlapAdapter.onDetachedFromRecyclerView
     * @since 3.1.5
     */
    fun onDetachedFromRecyclerView(adapter: RecyclerView.Adapter<*>, recyclerView: RecyclerView) {}
}