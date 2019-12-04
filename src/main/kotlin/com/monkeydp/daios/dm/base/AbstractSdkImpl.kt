package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.DmTestdata
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.daios.dms.sdk.main.SdkImpl.Classes
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractSdkImpl(override val kodein: Kodein) : SdkImpl {
    
    override val dsDefSet: Set<DsDef> by kodein.instance()
    override val classes = StdClasses()
    override val testdata: DmTestdata by kodein.instance()
    
    abstract inner class AbstractClasses : Classes {
        override val newConnFormKClass: KClass<out NewConnForm> by kodein.instance()
        
        override val dsVersionKClass: KClass<out DsVersion<*>> by kodein.instance()
        override val actionKClass: KClass<out Action<*>> by kodein.instance()
        override val targetKClass: KClass<out Target<*>> by kodein.instance()
        override val iconKClass: KClass<out Icon<*>> by kodein.instance()
    }
    
    inner class StdClasses : AbstractClasses()
}