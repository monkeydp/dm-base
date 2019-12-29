package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.node.AbstractNd
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
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