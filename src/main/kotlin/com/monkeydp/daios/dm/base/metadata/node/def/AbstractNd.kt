package com.monkeydp.daios.dm.base.metadata.node.def

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.StdNode
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNd(
        target: Target<*>,
        name: String = "",
        icon: Icon<*> = EMPTY_ICON
) : NodeDef {
    override val structName by lazy @JsonIgnore {
        if (this.javaClass.kotlin.isAbstract) "<no name for abstract>"
        else this.javaClass.simpleName.camelCase2List().lastOf(1).toLowerCase()
    }
    override val info = NodeInfo(target, name, icon)
    override var parent: NodeDef? = null
    override var children by Delegates.notNullSingleton<List<NodeDef>>()
    override var menuDef: MenuDef? = null
    
    override fun create(name: String?) =
            StdNode(
                    target = info.target,
                    name = name ?: info.name,
                    icon = info.icon
            )
    
    override fun toString() = this.info.name
}