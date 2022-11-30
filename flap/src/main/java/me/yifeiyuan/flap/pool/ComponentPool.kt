package me.yifeiyuan.flap.pool

import android.content.ComponentCallbacks2
import android.content.res.Configuration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import me.yifeiyuan.flap.FlapDebug

/**
 * 自定义的 RecycledViewPool，实现了 ComponentCallbacks2 ，可以在内存不足的时候清理缓存
 *
 * @see me.yifeiyuan.flap.Flap.withComponentPoolEnable 设置开关
 *
 * Created by 程序亦非猿 on 2021/9/22.
 *
 * Flap Github: <a>https://github.com/AlanCheen/Flap</a>
 * @author 程序亦非猿 [Follow me](<a> https://github.com/AlanCheen</a>)
 * @since 2020/9/22
 * @since 3.0.2
 */
open class ComponentPool : RecycledViewPool(), ComponentCallbacks2 {

    companion object {
        private const val TAG = "ComponentPool"
    }

    override fun setMaxRecycledViews(viewType: Int, max: Int) {
        super.setMaxRecycledViews(viewType, max)
        FlapDebug.d(TAG, "setMaxRecycledViews() called with: viewType = $viewType, max = $max")
    }

    override fun getRecycledViewCount(viewType: Int): Int {
        val result = super.getRecycledViewCount(viewType)
        FlapDebug.d(TAG, "getRecycledViewCount() called with: viewType = $viewType,result=$result")
        return result
    }

    override fun getRecycledView(viewType: Int): RecyclerView.ViewHolder? {
        val result = super.getRecycledView(viewType)
        FlapDebug.d(TAG, "getRecycledView() called with: viewType = $viewType,result=$result")
        return result
    }

    override fun putRecycledView(scrap: RecyclerView.ViewHolder) {
        super.putRecycledView(scrap)
        FlapDebug.d(TAG, "putRecycledView() called with: scrap = $scrap")
    }

    //参考 Glide 的 MemoryCache 实现
    override fun onTrimMemory(level: Int) {
        when (level) {
            in ComponentCallbacks2.TRIM_MEMORY_BACKGROUND..ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> {
                clear()
            }
            else -> {
            }
        }
        FlapDebug.d(TAG, "onTrimMemory() called with: level = $level")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {}

    override fun onLowMemory() {
        FlapDebug.d(TAG, "onLowMemory: ")
        clear()
    }

    override fun clear() {
        super.clear()
        FlapDebug.d("ComponentPool", "ComponentPool 执行清理缓存")
    }
}