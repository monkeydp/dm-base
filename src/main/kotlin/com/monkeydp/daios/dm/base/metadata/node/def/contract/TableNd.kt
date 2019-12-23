package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.AbstractNd
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TableNd : NodeDef {
    companion object {
        operator fun invoke(init: (TableNd.() -> Unit)? = null): TableNd = initInstance<StdTableNd>(init)
    }
}

abstract class AbstractTableNd : TableNd, AbstractNd()

private class StdTableNd : AbstractTableNd()