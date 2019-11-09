package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.initializer.SdkApiInitializer
import com.monkeydp.daios.dm.base.initializer.SdkClassesInitializer
import com.monkeydp.daios.dm.base.initializer.SdkEnumClassesInitializer
import com.monkeydp.daios.dms.sdk.SdkImpl
import com.monkeydp.daios.dms.sdk.SdkImpl.*
import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.form.SdkForm
import com.monkeydp.daios.dms.sdk.metadata.form.SdkFormContract
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.*
import org.reflections.Reflections
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.properties.Delegates
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractSdkImpl(private val dm: Dm) : SdkImpl {
    
    override val apis = StdApis()
    override val classes = StdClasses()
    override val enumClasses = StdEnumClasses()
    
    protected fun getReflections(
            packageName: String? = null,
            classLoader: ClassLoader? = null
    ): Reflections {
        var usedPackageName = packageName ?: dm.javaClass.`package`.name
        var usedClassLoader = classLoader ?: dm.javaClass.classLoader
        val urls =
                ClasspathHelper.forPackage(usedPackageName, usedClassLoader)
        return Reflections(ConfigurationBuilder()
                .setUrls(urls)
                .addClassLoader(usedClassLoader))
    }
    
    inner class StdApis : Apis {
        override var connApi by Delegates.notNullSingleton<ConnApi>()
        override var nodeApi by Delegates.notNullSingleton<NodeApi>()
        override var menuApi by Delegates.notNullSingleton<MenuApi>()
        override var formApi by Delegates.notNullSingleton<FormApi>()
        override var instrApi by Delegates.notNullSingleton<InstrApi>()
        
        init {
            SdkApiInitializer(this, getReflections())
        }
    }
    
    inner class StdClasses : Classes {
        override var newConnFormClass by Delegates.notNullSingleton<KClass<out NewConnForm>>()
        
        init {
            SdkClassesInitializer(this, formClassMap())
        }
        
        private fun formClassMap(): Map<Any, KClass<out Any>> =
                getReflections().getAnnotKClasses(SdkForm::class)
                        .map { kClass ->
                            val contract = kClass.getInterfaces()
                                    .matchOnce { it.hasAnnotation<SdkFormContract>() }
                            contract to kClass
                        }.toMap()
    }
    
    inner class StdEnumClasses : EnumClasses {
        override var dsVersionClass by Delegates.notNullSingleton<KClass<out DsVersion<*>>>()
        override var actionClass by Delegates.notNullSingleton<KClass<out Action<*>>>()
        override var targetClass by Delegates.notNullSingleton<KClass<out Target<*>>>()
        override var iconClass by Delegates.notNullSingleton<KClass<out Icon<*>>>()
        
        init {
            SdkEnumClassesInitializer(this, getReflections())
        }
    }
}