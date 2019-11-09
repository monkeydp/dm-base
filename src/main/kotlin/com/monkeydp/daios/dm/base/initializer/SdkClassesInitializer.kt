package com.monkeydp.daios.dm.base.initializer

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.tools.ext.ierror
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

class SdkClassesInitializer(implClasses: SdkImpl.Classes, classMap: Map<Any, KClass<*>>) {
    
    init {
        implClasses.javaClass.kotlin.memberProperties.forEach {
            val contractKClass = getFirstUpperBound(it)
            (it as KMutableProperty<*>).setter.call(implClasses, classMap.getValue(contractKClass))
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