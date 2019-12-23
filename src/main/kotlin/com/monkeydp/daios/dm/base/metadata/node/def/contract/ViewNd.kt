package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.AbstractNd
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewNd : NodeDef {
    companion object {
        operator fun invoke(init: (ViewNd.() -> Unit)? = null): ViewNd = initInstance<StdViewNd>(init)
    }
}

abstract class AbstractViewNd : ViewNd, AbstractNd()

internal class StdViewNd : AbstractViewNd()