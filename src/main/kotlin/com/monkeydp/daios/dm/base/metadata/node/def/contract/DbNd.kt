package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.std.StdDbNd
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface DbNd : NodeDef

fun dbNd(init: (DbNd.() -> Unit)? = null): DbNd = initInstance<StdDbNd>(init)