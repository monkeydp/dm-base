package com.monkeydp.daios.dm.base

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDefImpl
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDefImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.form.FormImpl
import com.monkeydp.tools.ext.singletonInstance
import com.monkeydp.tools.ext.singletonInstanceX
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.reflect.KClass

abstract class LocalConfig {
    abstract val nodeConfig: NodeConfig
    abstract val menuConfig: MenuConfig
    abstract val formConfig: FormConfig
    abstract val instrConfig: InstrConfig
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> getAnnotSingletons(reflections: Reflections, annotClass: KClass<out Annotation>) =
            reflections.getTypesAnnotatedWith(annotClass.java)
                    .map { it.singletonInstance() }.toList() as List<T>
    
    private fun getAnnotKClasses(reflections: Reflections, annotClass: KClass<out Annotation>) =
            reflections.getTypesAnnotatedWith(annotClass.java).map { it.kotlin }.toList()
    
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
            itemDefs.map { it.info.instr to it }.toMap()
        }
    }
    
    abstract inner class FormConfig {
        protected abstract val reflections: Reflections
        val formKClasses by lazy { getAnnotKClasses(reflections, FormImpl::class) }
        val formKClassMap by lazy {
            formKClasses.map {
                val instrKClass = it.java.getAnnotation(FormImpl::class.java).instrClass
                val instr = instrKClass.java.singletonInstanceX<Instruction>()
                instr to it
            }.toMap()
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