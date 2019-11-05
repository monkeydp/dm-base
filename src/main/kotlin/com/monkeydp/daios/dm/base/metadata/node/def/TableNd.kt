package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.TableNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface TableNd : NodeDef {
    fun create(name: String): TableNode
}