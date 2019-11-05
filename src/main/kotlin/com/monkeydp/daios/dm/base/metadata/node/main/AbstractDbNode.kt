package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.DbNd

/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractDbNode(
        def: DbNd,
        name: String
) : DbNode, AbstractNode(def, name)