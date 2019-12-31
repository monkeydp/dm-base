package com.monkeydp.daios.dm.base.ui.node.def

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface CollsNd : GroupNd {
    companion object {
        operator fun invoke(init: (CollsNd.() -> Unit)? = null): CollsNd =
                StdCollsNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractCollsNd : CollsNd, AbstractGroupNd() {
    override var name = "Collections"
}

private class StdCollsNd : AbstractCollsNd()