package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.dm.*
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.annot.MenuItemDefImpl
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.def.annot.NodeDefImpl
import com.monkeydp.tools.ext.getLogger
import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.ext.singletonInstance
import org.reflections.Reflections

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDm(shareConfig: DmShareConfig? = null) : Dm {
    
    companion object {
        private val log = getLogger()
        @Volatile
        private var isNodeStructInitialized = false
    }
    
    protected abstract val config: LocalConfig
    
    init {
        if (shareConfig != null) updateConfig(shareConfig)
    }
    
    protected abstract fun updateConfig(config: DmShareConfig)
    
    protected abstract inner class LocalConfig {
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
            open val itemDefMap: Map<Pair<Action<*>, Target<*>>, MenuItemDef>
                get() =
                    reflections.getTypesAnnotatedWith(MenuItemDefImpl::class.java).map {
                        val nd = it.singletonInstance() as MenuItemDef
                        val instr = nd.info.instr
                        Pair(instr.action, instr.target) to nd
                    }.toMap()
        }
    }
    
    /**
     * Should called by init{...} in subclass!
     */
    protected fun initialize() {
        log.info("Begin to register all dm static components!")
        DmImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    
        log.info("Begin to init metadata struct!")
        initNodeStruct()
        initMenuStruct()
        log.info("End to init metadata struct!")
    }
    
    private fun initNodeStruct() {
        if (isNodeStructInitialized) return
        recurAssignNodeDefChildren(config.node.struct)
        isNodeStructInitialized = true
    }
    
    private fun recurAssignNodeDefChildren(struct: JsonNode) {
        val defMap = config.node.defMap
        val fields = struct.fields()
        fields.forEach { (structName, subStruct) ->
            val def = defMap.getValue(structName)
            val children = mutableListOf<NodeDef>()
            subStruct.fields().forEach { (subStructName, _) ->
                children.add(defMap.getValue(subStructName))
            }
            def.children = children.toList()
            recurAssignNodeDefChildren(subStruct)
        }
    }
    
    private fun initMenuStruct() {
        val struct = config.menu.struct
        initNodeMenuStruct(struct["node"])
    }
    
    private fun initNodeMenuStruct(struct: JsonNode) {
        val menuName = "menu"
        val itemsName = "items"
        val instrName = "instr"
        val itemDefMap = config.menu.itemDefMap
        struct.forEach {
            val itemsStruct = it.get(menuName)?.get(itemsName)
            if (itemsStruct == null) return
            itemsStruct.forEach { itemStruct ->
                if (itemStruct[instrName] == null) {
                    if (itemStruct.size() != 1) ierror("Menu item struct without prop named `instr` can only have one prop, which key is `action` and value is `target`!")
                    val next = itemStruct.fields().next()
                    val action = DmImplRegistry.getEnum<Action<*>>(next.key.toUpperCase())
                    val target = DmImplRegistry.getEnum<Target<*>>(next.value.asText().toUpperCase())
                    val itemDef = itemDefMap.getValue(Pair(action, target))
                }
            }
        }
    }
}