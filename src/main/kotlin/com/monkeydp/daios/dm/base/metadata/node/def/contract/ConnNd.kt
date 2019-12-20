package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.std.StdConnNd
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ConnNd : NodeDef {
    fun create(cp: ConnProfile): ConnNode
}

fun connNd(init: (ConnNd.() -> Unit)? = null): ConnNd = initInstance<StdConnNd>(init)