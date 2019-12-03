package com.monkeydp.daios.dm.base.initializer

import com.monkeydp.daios.dms.sdk.main.SdkApi
import com.monkeydp.daios.dms.sdk.main.SdkApiContract
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.tools.ext.*
import org.reflections.Reflections
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

/**
 * @author iPotato
 * @date 2019/11/9
 */
class SdkApiInitializer(apis: SdkImpl.Apis, private val reflections: Reflections) {
    
    companion object {
        val log = getLogger()
    }
    
    init {
        val apiMap = apiMap()
        apis.javaClass.kotlin.memberProperties.forEach {
            val contractClass =
                    when (val contractType = it.returnType.javaType) {
                        is Class<*> -> contractType
                        is ParameterizedType -> contractType.rawType as Class<*>
                        else -> ierror("Unsupported contract type: ${contractType.typeName}")
                    }
            val api = apiMap[contractClass]
            if (api == null && isDebugMode()) {
                log.debugMode("${contractClass.name} not found!")
                return@forEach
            }
            (it as KMutableProperty<*>).setter.call(apis, api)
        }
    }
    
    private fun apiMap(): Map<Class<*>, Any> {
        val apis = reflections.getAnnotSingletons(SdkApi::class)
        return apis.map { api ->
            val interfaces = api.javaClass.kotlin.getInterfaces()
            interfaces.matchOne { it.hasAnnotation<SdkApiContract>() }.java to api
        }.toMap()
    }
}