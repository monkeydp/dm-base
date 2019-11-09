package com.monkeydp.daios.dm.base.initializer

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.api.SdkApi
import com.monkeydp.daios.dms.sdk.api.SdkApiContract
import com.monkeydp.tools.ext.getAnnotSingletons
import com.monkeydp.tools.ext.getInterfaces
import com.monkeydp.tools.ext.hasAnnotation
import com.monkeydp.tools.ext.matchOnce
import org.reflections.Reflections
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

/**
 * @author iPotato
 * @date 2019/11/9
 */
class SdkApiInitializer(apis: SdkImpl.Apis, private val reflections: Reflections) {
    
    init {
        val apiMap = apiMap()
        apis.javaClass.kotlin.memberProperties.forEach {
            val contractClass = it.returnType.javaType as Class<*>
            (it as KMutableProperty<*>).setter.call(apis, apiMap.getValue(contractClass))
        }
    }
    
    private fun apiMap(): Map<Class<*>, Any> {
        val apis = reflections.getAnnotSingletons(SdkApi::class)
        return apis.map { api ->
            val interfaces = api.javaClass.kotlin.getInterfaces()
            interfaces.matchOnce { it.hasAnnotation<SdkApiContract>() }.java to api
        }.toMap()
    }
}