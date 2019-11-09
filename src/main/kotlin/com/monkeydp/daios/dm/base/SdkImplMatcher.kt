package com.monkeydp.daios.dm.base

import com.monkeydp.tools.ext.getJavaInterfaces
import com.monkeydp.tools.ext.ierror
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
class SdkImplMatcher(
        private val expectedInterfaces: Set<Class<*>>,
        private val impls: Set<Any>
) {
    val capturedMap = impls.map {
        matchOnlyOneInterface(if (it is KClass<*>) it.java else it::class.java).kotlin to it
    }.toMap()
    
    private fun matchOnlyOneInterface(clazz: Class<*>): Class<*> {
        val allInterfaces = clazz.kotlin.getJavaInterfaces()
        val matchedInterfaces = mutableSetOf<Class<*>>()
        expectedInterfaces.forEach {
            if (allInterfaces.contains(it)) {
                matchedInterfaces.add(it)
            }
        }
        if (matchedInterfaces.size != 1) ierror("Matches interface 0 times or more then once, class is $clazz, following interfaces are matched: $matchedInterfaces")
        return matchedInterfaces.first()
    }
}