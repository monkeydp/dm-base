package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.AbstractNd
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface DbNd : NodeDef {
    companion object {
        operator fun invoke(init: (DbNd.() -> Unit)? = null): DbNd = initInstance<DbNdImpl>(init)
    }
}

abstract class AbstractDbNd : DbNd, AbstractNd()

private class DbNdImpl : AbstractDbNd()