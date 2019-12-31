package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewNd : NodeDef {
    companion object {
        operator fun invoke(init: (ViewNd.() -> Unit)? = null): ViewNd =
                StdViewNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractViewNd : ViewNd, AbstractNd()

internal class StdViewNd : AbstractViewNd()