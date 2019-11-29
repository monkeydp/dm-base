package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.main.SdkForm
import com.monkeydp.tools.ext.singletonInstance
import com.monkeydp.tools.ext.singletonInstanceX
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.reflect.KClass

abstract class LocalConfig {
    protected val reflections = getReflections()
    
    val formKClasses by lazy { reflections.getAnnotKClasses(SdkForm::class) }
    val formKClassMap by lazy {
        formKClasses.map {
            val instrKClass = it.java.getAnnotation(SdkForm::class.java).instrClass
            val instr = instrKClass.java.singletonInstanceX<Instruction>()
            instr to it
        }.toMap()
    }
    
    val parsers by lazy { reflections.getAnnotSingletons<InstrParser>(InstrParserImpl::class) }
    val parserMap by lazy { parsers.map { it.instr to it }.toMap() }
    
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
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> Reflections.getAnnotSingletons(annotClass: KClass<out Annotation>) =
            reflections.getTypesAnnotatedWith(annotClass.java)
                    .map { it.singletonInstance() }.toList() as List<T>
    
    private fun Reflections.getAnnotKClasses(annotClass: KClass<out Annotation>) =
            reflections.getTypesAnnotatedWith(annotClass.java).map { it.kotlin }.toList()
}