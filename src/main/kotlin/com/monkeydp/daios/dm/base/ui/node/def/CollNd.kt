package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface CollNd : NodeDef {
    companion object {
        operator fun invoke(init: (CollNd.() -> Unit)? = null): CollNd =
                StdCollNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractCollNd : CollNd, AbstractNd()

private class StdCollNd : AbstractCollNd()