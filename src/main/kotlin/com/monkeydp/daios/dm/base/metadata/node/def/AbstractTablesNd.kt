package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.StdTablesNode

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractTablesNd(
        name: String = "Tables"
) : TablesNd, AbstractGroupNd(name) {
    override fun create() = StdTablesNode(this)
}
