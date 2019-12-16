package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.std.StdViewNd
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewNd : NodeDef

fun view(init: ViewNd.() -> Unit) = initInstance<StdViewNd>(init)