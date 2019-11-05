package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.ViewNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface ViewNd : NodeDef {
    fun create(name: String): ViewNode
}