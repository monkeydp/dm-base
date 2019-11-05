package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.ViewNd

/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractViewNode(
        def: ViewNd,
        name: String
) : ViewNode, AbstractNode(def, name)