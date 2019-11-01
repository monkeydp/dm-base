package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractNd : NodeDef {
    override val structName: String
        get() {
            if (this.javaClass.kotlin.isAbstract) return "<no name for abstract>"
            return this.javaClass.simpleName.camelCase2List().lastOf(1).toLowerCase()
        }
    override var parent: NodeDef? = null
    override var children by Delegates.notNullSingleton<List<NodeDef>>()
    override var menu: Menu? = null
}