package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.K_CLASS
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.SINGLETON
import com.monkeydp.tools.ext.reflections.*
import org.reflections.Reflections
import org.reflections.scanners.FieldAnnotationsScanner
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

abstract class AbstractComponentConfig : ComponentConfig {
    
    private val reflections = Reflections(
            listOf(PackageName.sdk, PackageName.dm),
            javaClass.classLoader,
            *reflectionsDefaultScanners,
            FieldAnnotationsScanner()
    )
    
    override val componentsMap by lazy {
        val annotKClasses = reflections.getAnnotatedAnnotKClasses(KodeinComponent::class, true)
        annotKClasses.map { annotKClass ->
            val kodeinComponent = annotKClass.findAnnotation<KodeinComponent<*>>()!!
            annotKClass to when (kodeinComponent.type) {
                SINGLETON -> findSingletons(annotKClass)
                K_CLASS -> findKClass(annotKClass)
            }
        }.toMap()
    }
    
    private fun findSingletons(annotKClass: KClass<out Annotation>): Collection<Any> {
        val singletons = mutableListOf<Any>()
        val target = annotKClass.findAnnotation<Target>()!!
        target.allowedTargets.forEach {
            val part = when (it) {
                CLASS -> reflections.getAnnotatedSingletons(annotKClass)
                FIELD -> reflections.getAnnotatedFieldValues(annotKClass, true)
                else -> return@forEach
            }
            singletons.addAll(part)
        }
        return singletons.toList()
    }
    
    private fun findKClass(annotKClass: KClass<out Annotation>) = reflections.getAnnotatedKClasses(annotKClass)
}