package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.def.std.*
import kotlin.reflect.full.createInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
fun conn(init: StdConnNd.() -> Unit) = initNd<StdConnNd>(init)

fun db(init: StdDbNd.() -> Unit) = initNd<StdDbNd>(init)

fun tables(init: StdTablesNd.() -> Unit) = initNd<StdTablesNd>(init)

fun table(init: StdTableNd.() -> Unit) = initNd<StdTableNd>(init)

fun views(init: StdViewsNd.() -> Unit) = initNd<StdViewsNd>(init)

fun view(init: StdViewNd.() -> Unit) = initNd<StdViewNd>(init)

private inline fun <reified Nd : NodeDef> initNd(init: Nd.() -> Unit): Nd {
    val nd = Nd::class.createInstance()
    nd.init()
    return nd
}