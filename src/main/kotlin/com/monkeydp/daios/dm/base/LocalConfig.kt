package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemImpl
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDefImpl
import com.monkeydp.tools.ext.singletonInstance
import org.reflections.Reflections

abstract class LocalConfig {
    abstract val nodeConfig: NodeConfig
    abstract val menuConfig: MenuConfig
    
    abstract inner class NodeConfig {
        abstract val struct: JsonNode
        protected abstract val reflections: Reflections
        val defs: List<NodeDef> by lazy {
            reflections.getTypesAnnotatedWith(NodeDefImpl::class.java).map {
                it.singletonInstance() as NodeDef
            }.toList()
        }
        val defMap by lazy {
            defs.map { it.structName to it }.toMap()
        }
    }
    
    abstract inner class MenuConfig {
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