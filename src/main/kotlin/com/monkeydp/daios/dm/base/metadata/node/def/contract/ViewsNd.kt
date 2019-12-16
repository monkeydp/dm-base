package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.std.StdViewsNd
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewsNd : GroupNd

fun views(init: ViewsNd.() -> Unit) = initInstance<StdViewsNd>(init)