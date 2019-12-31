package com.monkeydp.daios.dm.base.ui.node.def

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface ViewsNd : GroupNd {
    companion object {
        operator fun invoke(init: (ViewsNd.() -> Unit)? = null): ViewsNd =
                StdViewsNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractViewsNd : ViewsNd, AbstractGroupNd()

private class StdViewsNd : AbstractViewsNd()