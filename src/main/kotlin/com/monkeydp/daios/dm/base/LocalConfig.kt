package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dm.base.metadata.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.metadata.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDefImpl
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDefImpl
import com.monkeydp.tools.ext.singletonInstance
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.reflect.KClass

abstract class LocalConfig {
    abstract val nodeConfig: NodeConfig
    abstract val menuConfig: MenuConfig
    abstract val instrConfig: InstrConfig
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> getAnnotSingletons(reflections: Reflections, annotClass: KClass<out Annotation>) =
            reflections.getTypesAnnotatedWith(annotClass.java)
                    .map { it.singletonInstance() }.toList() as List<T>
    
    abstract inner class NodeConfig {
        abstract val struct: JsonNode
        protected abstract val reflections: Reflections
        val defs by lazy { getAnnotSingletons<NodeDef>(reflections, NodeDefImpl::class) }
        val defMap by lazy { defs.map { it.structName to it }.toMap() }
    }
    
    abstract inner class MenuConfig {
        abstract val struct: JsonNode
        protected abstract val reflections: Reflections
        val itemDefs by lazy { getAnnotSingletons<MenuItemDef>(reflections, MenuItemDefImpl::class) }
        val itemDefMap by lazy {
            itemDefs.map { Pair(it.info.instr.action, it.info.instr.target) to it }.toMap()
        }
    }
    
    abstract inner class InstrConfig {
        protected abstract val reflections: Reflections
        val parsers by lazy { getAnnotSingletons<InstrParser>(reflections, InstrParserImpl::class) }
        val parserMap by lazy { parsers.map { it.instr to it }.toMap() }
    }
    
    protected fun getReflections(
            packageName: String = this.javaClass.`package`.name,
            classLoader: ClassLoader = this.javaClass.classLoader
    ): Reflections {
        val urls =
                ClasspathHelper.forPackage(packageName, classLoader)
        return Reflections(ConfigurationBuilder()
                .setUrls(urls)
                .addClassLoader(classLoader))
    }
}