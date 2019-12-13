package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dms.sdk.annot.SdkForm
import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.tools.ext.java.singletonX
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.K_CLASS
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.SINGLETON
import com.monkeydp.tools.ext.kotlin.getReflections
import com.monkeydp.tools.ext.reflections.*
import kotlin.reflect.full.findAnnotation

abstract class AbstractLocalConfig : LocalConfig {
    
    private val reflections = getReflections()
    
    override val componentsMap by lazy {
        val classLoader = javaClass.classLoader
        val reflections =
                reflections(listOf(PackageName.sdk, PackageName.dm), classLoader)
        val annotKClasses = reflections.getAnnotatedAnnotKClasses(KodeinComponent::class, true)
        annotKClasses.map { annotKClass ->
            val kodeinComponent = annotKClass.findAnnotation<KodeinComponent<*>>()!!
            annotKClass to when (kodeinComponent.type) {
                SINGLETON -> reflections.getAnnotatedSingletons(annotKClass)
                K_CLASS -> reflections.getAnnotatedKClasses(annotKClass)
            }
        }.toMap()
    }
    
    private val formAnnotKClass = SdkForm::class
    private val formKClasses by lazy { reflections.getAnnotatedKClasses(formAnnotKClass) }
    override val formKClassMap by lazy {
        formKClasses.map {
            val instrKClass = it.java.getAnnotation(formAnnotKClass.java).instrClass
            val instr = instrKClass.java.singletonX<Instruction>()
            instr to it
        }.toMap()
    }
    
    private val parsers by lazy { reflections.getAnnotatedSingletonsX<InstrParser>(InstrParserImpl::class) }
    override val parserMap by lazy { parsers.map { it.instr to it }.toMap() }
}