package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.node.AbstractNd
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface CollNd : NodeDef {
    companion object {
        operator fun invoke(init: (CollNd.() -> Unit)? = null): CollNd =
                initInstance<StdCollNd>(init)
    }
}

abstract class AbstractCollNd : CollNd, AbstractNd()

private class StdCollNd : AbstractCollNd()