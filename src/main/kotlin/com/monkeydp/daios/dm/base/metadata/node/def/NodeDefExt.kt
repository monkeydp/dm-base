package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.def.contract.*
import com.monkeydp.daios.dm.base.metadata.node.def.std.*
import com.monkeydp.tools.ext.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
fun conn(init: ConnNd.() -> Unit) = initInstance<StdConnNd>(init)

fun db(init: DbNd.() -> Unit) = initInstance<StdDbNd>(init)

fun tables(init: TablesNd.() -> Unit) = initInstance<StdTablesNd>(init)

fun table(init: TableNd.() -> Unit) = initInstance<StdTableNd>(init)

fun views(init: ViewsNd.() -> Unit) = initInstance<StdViewsNd>(init)

fun view(init: ViewNd.() -> Unit) = initInstance<StdViewNd>(init)