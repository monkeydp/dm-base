package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.SdkPackagePlaceHolder
import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.enumx.SdkEnum
import com.monkeydp.daios.dms.sdk.enumx.SdkEnumContract
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.getAnnotClasses
import com.monkeydp.tools.ext.getAnnotKClasses
import com.monkeydp.tools.ext.getAnnotSingletons
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.reflect.KClass

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
    
        private val apiInterfaces =
                getReflections(SdkPackagePlaceHolder.javaClass.`package`.name)
                        .getAnnotClasses(SdkApiContract::class)
                        .filter { it.isInterface }.map { it }.toSet()
        
        private val apiMap by lazy {
            SdkImplMatcher(
                    expectedInterfaces = apiInterfaces,
                    impls = getReflections().getAnnotSingletons(SdkApi::class)
            ).capturedMap
        }
        
        override val connApi by lazy { apiMap.getValue(ConnApi::class) as ConnApi }
        override val nodeApi by lazy { apiMap.getValue(NodeApi::class) as NodeApi }
        override val menuApi by lazy { apiMap.getValue(MenuApi::class) as MenuApi }
        override val formApi by lazy { apiMap.getValue(FormApi::class) as FormApi }
        override val instrApi by lazy { apiMap.getValue(InstrApi::class) as InstrApi }
    }
    
    @Suppress("UNCHECKED_CAST")
    public inner class StdEnumClasses : SdkImpl.EnumClasses {
        
        private val enumInterfaces =
                getReflections(SdkPackagePlaceHolder.javaClass.`package`.name)
                        .getAnnotClasses(SdkEnumContract::class)
                        .filter { it.isInterface }.map { it }.toSet()
        
        private val enumMap = SdkImplMatcher(
                expectedInterfaces = enumInterfaces,
                impls = getReflections().getAnnotKClasses(SdkEnum::class)
        ).capturedMap
        
        override val dsVersionClass = enumMap.getValue(DsVersion::class) as KClass<out DsVersion<*>>
        override val actionClass = enumMap.getValue(Action::class) as KClass<out Action<*>>
        override val targetClass = enumMap.getValue(Target::class) as KClass<out Target<*>>
        override val iconClass = enumMap.getValue(Icon::class) as KClass<out Icon<*>>
    }
}