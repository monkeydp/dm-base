package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.ViewsNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface ViewsNd : GroupNd {
    fun create(): ViewsNode
}