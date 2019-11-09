package com.monkeydp.daios.dm.base.initializer

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.enumx.EnumxHelper
import com.monkeydp.daios.dms.sdk.enumx.SdkEnum
import com.monkeydp.daios.dms.sdk.enumx.SdkEnumContract
import com.monkeydp.daios.dms.sdk.metadata.form.SdkForm
import com.monkeydp.daios.dms.sdk.metadata.form.SdkFormContract
import com.monkeydp.tools.ext.*
import org.reflections.Reflections
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
import sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

/**
 * @author iPotato
 * @date 2019/11/9
 */

class SdkClassesInitializer(implClasses: SdkImpl.Classes, private val reflections: Reflections) {
    
    private val formMap: Map<Any, KClass<out Any>> =
            reflections.getAnnotKClasses(SdkForm::class)
                    .map { kClass ->
                        val contract = kClass.getInterfaces()
                                .matchOne { it.hasAnnotation<SdkFormContract>() }
                        contract to kClass
                    }.toMap()
    
    private val enumMap: Map<Any, KClass<out Enumx<*>>> =
            reflections.getAnnotKClasses(SdkEnum::class).map {
                @Suppress("UNCHECKED_CAST")
                it as KClass<out Enumx<*>>
                EnumxHelper.getEnumxContract(it) to it
            }.toMap()
    
    
    private val mapMap = mapOf<KClass<out Annotation>, Map<Any, KClass<out Any>>>(
            SdkFormContract::class to formMap,
            SdkEnumContract::class to enumMap
    )
    
    init {
        implClasses.javaClass.kotlin.memberProperties.forEach { kp ->
            val contractKClass = getFirstUpperBound(kp)
            val annotKClass = mapMap.keys.matchOne { contractKClass.java.hasAnnotation(it.java) }
            val map = mapMap.getValue(annotKClass)
            (kp as KMutableProperty<*>).setter.call(implClasses, map.getValue(contractKClass))
        }
    }
    
    
    private fun getFirstUpperBound(kp: KProperty<*>): KClass<*> {
        val returnJavaType = kp.returnType.javaType as ParameterizedTypeImpl
        val wildcardType = returnJavaType.actualTypeArguments[0] as WildcardTypeImpl
        return when (val type = wildcardType.upperBounds[0]) {
            is ParameterizedTypeImpl -> type.rawType!!.kotlin
            is Class<*> -> type.kotlin
            else -> ierror("Unknown type $type!")
        }
    }
}