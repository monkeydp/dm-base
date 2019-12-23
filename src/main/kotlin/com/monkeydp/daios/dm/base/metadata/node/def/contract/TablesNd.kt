package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface TablesNd : GroupNd{
    companion object {
        operator fun invoke(init: (TablesNd.() -> Unit)? = null): TablesNd = initInstance<TablesNdImpl>(init)
    }
}

abstract class AbstractTablesNd : TablesNd, AbstractGroupNd()

private class TablesNdImpl : AbstractTablesNd()