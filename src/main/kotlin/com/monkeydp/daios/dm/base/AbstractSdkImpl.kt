package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.getAnnotSingletons
import com.monkeydp.tools.ext.getInterfaces
import com.monkeydp.tools.ext.ierror
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
    override val classes = StdClasses()
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
        private val apiKClasses =
                getReflections(SdkApi::class.java.`package`.name)
                        .getSubTypesOf(Any::class.java)
                        .filter { it.isInterface }.map { it.kotlin }.toSet()
        
        protected val reflections = getReflections()
        private val apiMap by lazy {
            reflections.getAnnotSingletons(SdkApi::class)
                    .map { matchOnlyOneInterface(it::class) to it }.toMap()
        }
        
        override val connApi by lazy { apiMap.getValue(ConnApi::class) as ConnApi }
        override val nodeApi by lazy { apiMap.getValue(NodeApi::class) as NodeApi }
        override val menuApi by lazy { apiMap.getValue(MenuApi::class) as MenuApi }
        override val formApi by lazy { apiMap.getValue(FormApi::class) as FormApi }
        override val instrApi by lazy { apiMap.getValue(InstrApi::class) as InstrApi }
        
        private fun matchOnlyOneInterface(kClass: KClass<*>): KClass<*> {
            var interfaze: KClass<*>? = null
            var matchTimes = 0
            val interfaces = kClass.getInterfaces()
            apiKClasses.forEach {
                if (interfaces.contains(it)) {
                    interfaze = it
                    matchTimes++
                }
            }
            if (matchTimes != 1) ierror("Matches api class 0 times or more then once, class is $kClass, match times is ${matchTimes}")
            return interfaze!!
        }
    }
    
    inner class StdClasses : SdkImpl.Classes {
        override val newConnFormClass: KClass<out NewConnForm>
            get() = TODO("not implemented")
    }
    
    inner class StdEnumClasses : SdkImpl.EnumClasses {
        override val dsVersionClass: KClass<out DsVersion<*>>
            get() = TODO("not implemented")
        override val actionClass: KClass<out Action<*>>
            get() = TODO("not implemented")
        override val targetClass: KClass<out Target<*>>
            get() = TODO("not implemented")
        override val iconClass: KClass<out Icon<*>>
            get() = TODO("not implemented")
        
    }
}