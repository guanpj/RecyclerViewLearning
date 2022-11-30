package com.me.guanpj.recyclerviewlearning

import android.content.res.Resources

/**
 * @author Drakeet Xu
 */
val Number.dp: Int get() = (toInt() * Resources.getSystem().displayMetrics.density).toInt()
