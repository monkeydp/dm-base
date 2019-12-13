package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.K_CLASS
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type.SINGLETON
import com.monkeydp.tools.ext.reflections.getAnnotatedAnnotKClasses
import com.monkeydp.tools.ext.reflections.getAnnotatedKClasses
import com.monkeydp.tools.ext.reflections.getAnnotatedSingletons
import com.monkeydp.tools.ext.reflections.reflections
import kotlin.reflect.full.findAnnotation

abstract class AbstractComponentConfig : ComponentConfig {
    
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
}