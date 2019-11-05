package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.NodeInfo

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface NodeDef {
    val structName: String
    val info: NodeInfo
    var parent: NodeDef?
    var children: List<NodeDef>
    var menuDef: MenuDef?
    
    fun create(name: String? = null): Node
}