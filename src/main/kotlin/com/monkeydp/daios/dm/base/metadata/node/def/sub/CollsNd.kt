package com.monkeydp.daios.dm.base.metadata.node.def.sub

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface CollsNd : GroupNd {
    companion object {
        operator fun invoke(init: (CollsNd.() -> Unit)? = null): CollsNd =
                initInstance<StdCollsNd>(init)
    }
}

abstract class AbstractCollsNd : CollsNd, AbstractGroupNd() {
    override var name = "Collections"
}

private class StdCollsNd : AbstractCollsNd()