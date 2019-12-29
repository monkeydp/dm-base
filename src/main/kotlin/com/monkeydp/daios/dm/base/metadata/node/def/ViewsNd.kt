package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewsNd : GroupNd {
    companion object {
        operator fun invoke(init: (ViewsNd.() -> Unit)? = null): ViewsNd = initInstance<StdViewsNd>(init)
    }
}

abstract class AbstractViewsNd : ViewsNd, AbstractGroupNd()

private class StdViewsNd : AbstractViewsNd()