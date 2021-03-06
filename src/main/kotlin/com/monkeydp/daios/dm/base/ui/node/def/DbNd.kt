package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface DbNd : NodeDef {
    companion object {
        operator fun invoke(init: (DbNd.() -> Unit)? = null): DbNd =
                StdDbNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractDbNd : DbNd, AbstractNd()

private class StdDbNd : AbstractDbNd()