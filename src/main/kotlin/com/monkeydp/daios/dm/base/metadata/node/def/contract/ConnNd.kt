package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.AbstractNd
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.daios.dms.sdk.metadata.node.connNode
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ConnNd : NodeDef {
    fun create(cp: ConnProfile): ConnNode
    
    companion object {
        operator fun invoke(init: (ConnNd.() -> Unit)? = null): ConnNd = initInstance<ConnNdImpl>(init)
    }
}

abstract class AbstractConnNd : ConnNd, AbstractNd() {
    override fun create(cp: ConnProfile) =
            connNode(
                    cp = cp,
                    defId = id,
                    target = target,
                    icon = icon
            )
}

private class ConnNdImpl : AbstractConnNd()