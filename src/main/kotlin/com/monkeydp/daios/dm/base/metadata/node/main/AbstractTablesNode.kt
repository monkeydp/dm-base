package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.TablesNd

/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractTablesNode(
        def: TablesNd
) : TablesNode, AbstractNode(def)