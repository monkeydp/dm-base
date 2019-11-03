package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.TableNd
import com.monkeydp.daios.dms.sdk.metadata.node.main.AbstractNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractTableNode(
        def: TableNd,
        name: String
) : TableNode, AbstractNode(def, name)