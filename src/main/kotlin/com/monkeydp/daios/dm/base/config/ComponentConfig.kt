package com.monkeydp.daios.dm.base.config

import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/9
 */
interface ComponentConfig {
    val componentsMap: Map<KClass<out Annotation>, Set<Any>>
    val components get() = componentsMap.values.toList().flatten()
}