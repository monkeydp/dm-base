package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.std.StdTableNd
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TableNd : NodeDef

fun table(init: TableNd.() -> Unit) = initInstance<StdTableNd>(init)