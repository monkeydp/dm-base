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
    var id: Int
    var target: Target<*>
    var name: String
    var icon: Icon<*>
    var parent: NodeDef?
    val children: List<NodeDef>
    var menuDef: MenuDef?
    
    /**
     * Add a child by "+"
     */
    operator fun NodeDef.unaryPlus()
    
    /**
     * Create a node by current node def
     */
    fun create(name: String? = null): Node
}