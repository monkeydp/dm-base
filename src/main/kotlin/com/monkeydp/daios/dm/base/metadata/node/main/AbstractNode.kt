package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.Node


/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractNode : Node {
    
    override val target: Target<*>
    override val name: String
    override val icon: Icon<*>
    
    protected constructor(def: NodeDef, name: String? = null) {
        val info = def.info
        target = info.target
        this.name = name ?: info.name
        icon = info.icon
    }
}