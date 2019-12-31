package com.monkeydp.daios.dm.base.ui.node.def

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TablesNd : GroupNd {
    companion object {
        operator fun invoke(init: (TablesNd.() -> Unit)? = null): TablesNd =
                StdTablesNd().apply { init?.invoke(this) }
    }
}

abstract class AbstractTablesNd : TablesNd, AbstractGroupNd()

private class StdTablesNd : AbstractTablesNd()