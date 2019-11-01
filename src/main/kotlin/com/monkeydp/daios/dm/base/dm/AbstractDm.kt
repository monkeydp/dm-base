package com.monkeydp.daios.dm.base.dm

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistrar
import com.monkeydp.daios.dms.sdk.dm.DmShareConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.def.annot.NodeDefImpl
import com.monkeydp.daios.dms.sdk.metadata.node.struct.NodeStructWrapper
import com.monkeydp.tools.ext.getLogger
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
    protected abstract val reflections: Reflections
    
    init {
        if (shareConfig != null) updateConfig(shareConfig)
    }
    
    protected abstract fun updateConfig(config: DmShareConfig)
    
    protected abstract inner class LocalConfig {
        abstract val node: Node
        
        abstract inner class Node {
            abstract val structWrapper: NodeStructWrapper
            open val defMap: Map<String, NodeDef>
                get() =
                    reflections.getTypesAnnotatedWith(NodeDefImpl::class.java).map {
                        val nd = it.singletonInstance() as NodeDef
                        nd.structName to nd
                    }.toMap()
        }
    }
    
    /**
     * Should called by init{...} in subclass!
     */
    protected fun initialize() {
        log.info("Begin to register all dm static components!")
        initNodeStruct()
        DmImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    }
    
    /**
     * Initialize node structure
     */
    private fun initNodeStruct() {
        if (isNodeStructInitialized) return
        val struct = config.node.structWrapper.structure
        recurAssignNodeDefChildren(struct)
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
}