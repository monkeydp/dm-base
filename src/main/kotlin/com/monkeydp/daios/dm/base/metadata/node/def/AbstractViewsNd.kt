package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.StdViewsNode

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractViewsNd(
        name: String = "Views"
) : ViewsNd, AbstractGroupNd(name) {
    override fun create() = StdViewsNode(this)
}