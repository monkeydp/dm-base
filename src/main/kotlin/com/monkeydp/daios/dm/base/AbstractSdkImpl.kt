package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.enumx.EnumxHelper
import com.monkeydp.daios.dms.sdk.enumx.SdkEnum
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.*
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
import sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractSdkImpl : SdkImpl {
    
    override val apis = StdApis()
    override val enumClasses = StdEnumClasses()
    
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
    
    inner class StdApis : SdkImpl.Apis {
    
        override var connApi by Delegates.notNullSingleton<ConnApi>()
        override var nodeApi by Delegates.notNullSingleton<NodeApi>()
        override var menuApi by Delegates.notNullSingleton<MenuApi>()
        override var formApi by Delegates.notNullSingleton<FormApi>()
        override var instrApi by Delegates.notNullSingleton<InstrApi>()
    
        init {
            val apiMap = apiMap()
            this.javaClass.kotlin.memberProperties.forEach {
                val contractClass = it.returnType.javaType as Class<*>
                (it as KMutableProperty<*>).setter.call(this, apiMap.getValue(contractClass))
            }
        }
    
        private fun apiMap(): Map<Class<*>, Any> {
            val apis = getReflections().getAnnotSingletons(SdkApi::class)
            return apis.map { api ->
                val interfaces = api.javaClass.kotlin.getInterfaces()
                interfaces.matchOnce { it.hasAnnotation<SdkApiContract>() }.java to api
            }.toMap()
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    public inner class StdEnumClasses : SdkImpl.EnumClasses {
        override var dsVersionClass by Delegates.notNullSingleton<KClass<out DsVersion<*>>>()
        override var actionClass by Delegates.notNullSingleton<KClass<out Action<*>>>()
        override var targetClass by Delegates.notNullSingleton<KClass<out Target<*>>>()
        override var iconClass by Delegates.notNullSingleton<KClass<out Icon<*>>>()
    
        init {
            val enumxClassMap = enumxClassMap()
            this.javaClass.kotlin.memberProperties.forEach {
                val contractKClass = getFirstUpperBound(it) as KClass<out Enumx<*>>
                (it as KMutableProperty<*>).setter.call(this, enumxClassMap.getValue(contractKClass))
            }
        }
    
        private fun getFirstUpperBound(kp: KProperty<*>): KClass<*> {
            val returnJavaType = kp.returnType.javaType as ParameterizedTypeImpl
            val wildcardType = returnJavaType.actualTypeArguments[0] as WildcardTypeImpl
            return (wildcardType.upperBounds[0] as ParameterizedTypeImpl).rawType!!.kotlin
        }
    
        private fun enumxClassMap(): Map<KClass<out Enumx<*>>, KClass<out Enumx<*>>> {
            val enumxKClasses = getReflections().getAnnotKClasses(SdkEnum::class)
            return enumxKClasses.map {
                it as KClass<out Enumx<*>>
                val enumxKClass = it
                EnumxHelper.getEnumxContract(enumxKClass) to it
            }.toMap()
        }
    }
}