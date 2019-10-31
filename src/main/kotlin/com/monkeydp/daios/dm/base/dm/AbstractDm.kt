package com.monkeydp.daios.dm.base.dm

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistrar
import com.monkeydp.daios.dms.sdk.dm.DmShareConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.metadata.node.struct.NodeStructWrapper
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.getClassname
import com.monkeydp.tools.ext.getLogger
import com.monkeydp.tools.ext.singletonInstance
import com.monkeydp.tools.util.FileUtil

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
    
    private var classLoader = Thread.currentThread().contextClassLoader
    protected abstract val config: LocalConfig
    
    init {
        if (shareConfig != null) {
            classLoader = shareConfig.classLoader
            updateConfig(shareConfig)
        }
    }
    
    private val nodeDefMap by lazy {
        val files = FileUtil.listFiles(config.node.defDirpath)
        files.map { file ->
            val classname = file.getClassname(config.classesDirpath)
            val nd = classLoader.loadClass(classname).singletonInstance() as NodeDef
            nd.structName to nd
        }.toMap()
    }
    
    protected abstract fun updateConfig(config: DmShareConfig)
    
    protected interface LocalConfig {
        val classesDirpath: String
        val node: Node
        
        interface Node {
            val structWrapper: NodeStructWrapper
            val defDirpath: String
        }
    }
    
    protected fun registerStaticComponents() {
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
        val fields = struct.fields()
        fields.forEach { (structName, subStruct) ->
            val def = nodeDefMap.getValue(structName)
            val children = mutableListOf<NodeDef>()
            subStruct.fields().forEach { (subStructName, _) ->
                children.add(nodeDefMap.getValue(subStructName))
            }
            def.children = children.toList()
            recurAssignNodeDefChildren(subStruct)
        }
    }
}