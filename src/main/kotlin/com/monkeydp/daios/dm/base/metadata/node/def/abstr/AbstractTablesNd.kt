package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.TablesNd

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractTablesNd(
        name: String = "Tables"
) : TablesNd, AbstractGroupNd(name)