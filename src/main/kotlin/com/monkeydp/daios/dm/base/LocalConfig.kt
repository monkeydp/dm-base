package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemImpl
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.def.annot.NodeDefImpl
import com.monkeydp.tools.ext.singletonInstance
import org.reflections.Reflections

abstract class LocalConfig {
    abstract val node: Node
    abstract val menu: Menu
    
    abstract inner class Node {
        abstract val struct: JsonNode
        protected abstract val reflections: Reflections
        open val defMap: Map<String, NodeDef>
            get() =
                reflections.getTypesAnnotatedWith(NodeDefImpl::class.java).map {
                    val nd = it.singletonInstance() as NodeDef
                    nd.structName to nd
                }.toMap()
    }
    
    abstract inner class Menu {
        abstract val struct: JsonNode
        protected abstract val reflections: Reflections
        open val itemMap: Map<Pair<Action<*>, Target<*>>, MenuItem>
            get() =
                reflections.getTypesAnnotatedWith(MenuItemImpl::class.java).map {
                    val nd = it.singletonInstance() as MenuItem
                    val instr = nd.instr
                    Pair(instr.action, instr.target) to nd
                }.toMap()
    }
}