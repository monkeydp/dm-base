package com.monkeydp.daios.dm.base.initializer

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.enumx.EnumxHelper
import com.monkeydp.daios.dms.sdk.enumx.SdkEnum
import com.monkeydp.tools.ext.getAnnotKClasses
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
@Suppress("UNCHECKED_CAST")
class SdkEnumClassesInitializer(enumClasses: SdkImpl.EnumClasses, private val reflections: Reflections) {
    
    init {
        val enumxClassMap = enumxClassMap()
        enumClasses.javaClass.kotlin.memberProperties.forEach {
            val contractKClass = getFirstUpperBound(it) as KClass<out Enumx<*>>
            (it as KMutableProperty<*>).setter.call(enumClasses, enumxClassMap.getValue(contractKClass))
        }
    }
    
    private fun getFirstUpperBound(kp: KProperty<*>): KClass<*> {
        val returnJavaType = kp.returnType.javaType as ParameterizedTypeImpl
        val wildcardType = returnJavaType.actualTypeArguments[0] as WildcardTypeImpl
        return (wildcardType.upperBounds[0] as ParameterizedTypeImpl).rawType!!.kotlin
    }
    
    private fun enumxClassMap(): Map<KClass<out Enumx<*>>, KClass<out Enumx<*>>> {
        val enumxKClasses = reflections.getAnnotKClasses(SdkEnum::class)
        return enumxKClasses.map {
            it as KClass<out Enumx<*>>
            val enumxKClass = it
            EnumxHelper.getEnumxContract(enumxKClass) to it
        }.toMap()
    }
}