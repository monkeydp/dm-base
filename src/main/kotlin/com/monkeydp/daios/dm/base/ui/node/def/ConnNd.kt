package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.ConnNode
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ConnNd : NodeDef {
    fun create(cp: ConnProfile): ConnNode
    
    companion object {
        operator fun invoke(init: (ConnNd.() -> Unit)? = null): ConnNd = initInstance<StdConnNd>(init)
    }
}

abstract class AbstractConnNd : ConnNd, AbstractNd() {
    override fun create(cp: ConnProfile) =
            ConnNode(
                    cp = cp,
                    defId = id,
                    target = target,
                    icon = icon,
                    menuDefId = menuDef?.id
            )
}

private class StdConnNd : AbstractConnNd()