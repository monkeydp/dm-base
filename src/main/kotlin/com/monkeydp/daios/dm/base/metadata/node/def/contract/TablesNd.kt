package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.std.StdTablesNd
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TablesNd : GroupNd

fun tablesNd(init: (TablesNd.() -> Unit)? = null): TablesNd = initInstance<StdTablesNd>(init)