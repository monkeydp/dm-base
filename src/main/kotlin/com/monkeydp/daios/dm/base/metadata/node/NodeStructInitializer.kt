package com.monkeydp.daios.dm.base.metadata.node

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dm.base.LocalConfig.NodeConfig
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/11/3
 */
@Deprecated("")
class NodeStructInitializer(config: NodeConfig) {
    
    companion object {
        @Volatile
        private var isInitialized = false
    }
    
    private val config: NodeConfig
    
    init {
        this.config = config
        startParsing()
    }
    
    private fun startParsing() {
        if (isInitialized) return
        synchronized(isInitialized) {
            recurAssignNodeDefChildren(config.struct)
            isInitialized = true
        }
    }
    
    private fun recurAssignNodeDefChildren(struct: JsonNode) {
        val defMap = config.defMap
        val fields = struct.fields()
        fields.forEach { (structName, subStruct) ->
            val def = defMap.getValue(structName)
            val children = mutableListOf<NodeDef>()
            subStruct.fields().forEach { (subStructName, _) ->
                val child = defMap.getValue(subStructName)
                child.parent = def
                children.add(child)
            }
//            def.children = children.toList()
            recurAssignNodeDefChildren(subStruct)
        }
    }
}