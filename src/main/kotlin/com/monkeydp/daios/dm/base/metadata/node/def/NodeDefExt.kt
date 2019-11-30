package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.def.contract.*
import com.monkeydp.daios.dm.base.metadata.node.def.std.*
import kotlin.reflect.full.createInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
fun conn(init: ConnNd.() -> Unit) = initNd<StdConnNd>(init)

fun db(init: DbNd.() -> Unit) = initNd<StdDbNd>(init)

fun tables(init: TablesNd.() -> Unit) = initNd<StdTablesNd>(init)

fun table(init: TableNd.() -> Unit) = initNd<StdTableNd>(init)

fun views(init: ViewsNd.() -> Unit) = initNd<StdViewsNd>(init)

fun view(init: ViewNd.() -> Unit) = initNd<StdViewNd>(init)

private inline fun <reified Nd : NodeDef> initNd(init: Nd.() -> Unit): Nd {
    val nd = Nd::class.createInstance()
    nd.init()
    return nd
}