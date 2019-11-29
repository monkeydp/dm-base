package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.node.StdNode
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNd(
        override var target: Target<*>,
        override var name: String = "",
        override var icon: Icon<*> = EMPTY_ICON
) : NodeDef {
    override val structName by lazy @JsonIgnore {
        if (this.javaClass.kotlin.isAbstract) "<no name for abstract>"
        else this.javaClass.simpleName.camelCase2List().lastOf(1).toLowerCase()
    }
    override var parent: NodeDef? = null
    private val _children = mutableListOf<NodeDef>()
    override val children
        get() = _children.toList()
    
    override var menuDef: MenuDef? = null
    
    operator fun NodeDef.unaryPlus() {
        _children.add(this)
    }
    
    override fun create(name: String?) =
            StdNode(
                    target = target,
                    name = name ?: this.name,
                    icon = icon,
                    childTargets = childTargets
            )
    
    override fun toString() = this.name
}