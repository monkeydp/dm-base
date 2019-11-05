package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.DbNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface DbNd : NodeDef {
    fun create(name: String): DbNode
}