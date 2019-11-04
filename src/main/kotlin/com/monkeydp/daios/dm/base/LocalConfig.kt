package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemDef
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDefImpl
import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDefImpl
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
        open val itemDefMap: Map<Pair<Action<*>, Target<*>>, MenuItemDef>
            get() =
                reflections.getTypesAnnotatedWith(MenuItemDefImpl::class.java).map {
                    val mid = it.singletonInstance() as MenuItemDef
                    val instr = mid.info.instr
                    Pair(instr.action, instr.target) to mid
                }.toMap()
    }
}