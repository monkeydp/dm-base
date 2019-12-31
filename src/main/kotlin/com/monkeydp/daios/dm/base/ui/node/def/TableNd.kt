package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TableNd : NodeDef {
    companion object {
        operator fun invoke(init: (TableNd.() -> Unit)? = null): TableNd =
                StdTableNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractTableNd : TableNd, AbstractNd()

private class StdTableNd : AbstractTableNd()