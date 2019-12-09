package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.config.putDmKodein
import com.monkeydp.daios.dm.base.ext.getDatasourceByClassname
import com.monkeydp.daios.dms.sdk.dm.DmApp
import com.monkeydp.daios.dms.sdk.dm.DmConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.exception.handler.IgnoreException
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistrar
import com.monkeydp.tools.ext.getLogger
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/4
 */
abstract class AbstractDmApp(config: DmConfig) : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    override val datasource = getDatasourceByClassname()
    
    private val dmKodein: Kodein
    override val sdkImpl: SdkImpl
    
    init {
        dmKodein = initDmKodein(config.kotlinModule)
        putDmKodein(this, dmKodein)
        sdkImpl = StdSdkImpl(dmKodein)
        registerImpl(sdkImpl)
    }
    
    abstract fun initDmKodein(vararg modules: Kodein.Module): Kodein
    
    @IgnoreException(Kodein.NotFoundException::class)
    private fun registerImpl(sdkImpl: SdkImpl) {
        SdkImplRegistrar.registerAll(sdkImpl, datasource)
        DmTestdataRegistrar.registerAll(sdkImpl.testdata)
    }
}