package com.monkeydp.daios.dm.base.metadata.node.def.sub

import com.monkeydp.daios.dms.sdk.metadata.node.AbstractNd
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface DbNd : NodeDef {
    companion object {
        operator fun invoke(init: (DbNd.() -> Unit)? = null): DbNd = initInstance<StdDbNd>(init)
    }
}

abstract class AbstractDbNd : DbNd, AbstractNd()

private class StdDbNd : AbstractDbNd()