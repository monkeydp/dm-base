package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef
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