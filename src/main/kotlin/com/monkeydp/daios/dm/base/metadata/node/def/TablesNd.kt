package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.TablesNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface TablesNd : GroupNd {
    fun create(): TablesNode
}