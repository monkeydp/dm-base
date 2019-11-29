package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.node.Node

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface NodeDef {
    val structName: String
    val target: Target<*>
    val name: String
    val icon: Icon<*>
    var parent: NodeDef?
    var children: List<NodeDef>
    val childTargets: List<Target<*>>
        get() = children.map { it.target }
    var menuDef: MenuDef?
    
    fun create(name: String? = null): Node
}