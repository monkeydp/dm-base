package com.monkeydp.daios.dm.base.metadata.node.def.sub

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface CollNd : GroupNd {
    companion object {
        operator fun invoke(init: (CollNd.() -> Unit)? = null): CollNd =
                initInstance<StdCollNd>(init)
    }
}

abstract class AbstractCollNd : CollNd, AbstractGroupNd()

private class StdCollNd : AbstractCollNd()