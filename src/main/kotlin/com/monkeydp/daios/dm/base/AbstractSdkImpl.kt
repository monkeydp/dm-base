package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.initializer.SdkApiInitializer
import com.monkeydp.daios.dm.base.initializer.SdkClassesInitializer
import com.monkeydp.daios.dms.sdk.api.*
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.daios.dms.sdk.main.SdkImpl.Apis
import com.monkeydp.daios.dms.sdk.main.SdkImpl.Classes
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.getReflections
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractSdkImpl(private val dm: Dm) : SdkImpl {
    
    override val apis = StdApis()
    override val classes = StdClasses()
    
    inner class StdApis : Apis {
        override var connApi by Delegates.notNullSingleton<ConnApi>()
        override var nodeApi by Delegates.notNullSingleton<NodeApi>()
        override var menuApi by Delegates.notNullSingleton<MenuApi>()
        override var formApi by Delegates.notNullSingleton<FormApi>()
        override var instrApi by Delegates.notNullSingleton<InstrApi>()
        
        init {
            SdkApiInitializer(this, dm.getReflections())
        }
    }
    
    inner class StdClasses : Classes {
        override var newConnFormKClass by Delegates.notNullSingleton<KClass<out NewConnForm>>()
    
        override var dsVersionKClass by Delegates.notNullSingleton<KClass<out DsVersion<*>>>()
        override var actionKClass by Delegates.notNullSingleton<KClass<out Action<*>>>()
        override var targetKClass by Delegates.notNullSingleton<KClass<out Target<*>>>()
        override var iconKClass by Delegates.notNullSingleton<KClass<out Icon<*>>>()
        
        init {
            SdkClassesInitializer(this, dm.getReflections())
        }
    }
}