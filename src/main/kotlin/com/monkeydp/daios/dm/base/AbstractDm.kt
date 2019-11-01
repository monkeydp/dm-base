package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dm.base.metadata.menu.MenuStructInitializer
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistrar
import com.monkeydp.daios.dms.sdk.dm.DmShareConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.getLogger

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
        MenuStructInitializer(config)
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
}