package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.DmApp
import com.monkeydp.daios.dms.sdk.dm.DmConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistrar
import com.monkeydp.tools.ext.camelCaseFirst
import com.monkeydp.tools.ext.debugMode
import com.monkeydp.tools.ext.getLogger
import com.monkeydp.tools.ext.isDebugMode
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/4
 */
abstract class AbstractDmApp(config: DmConfig) : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    private val dsName = javaClass.simpleName.camelCaseFirst()
    override val datasource = Datasource.valueOf(dsName.toUpperCase())
    
    private val kodein: Kodein
    override val sdkImpl: SdkImpl
    
    init {
        kodein = initKodein(config.kotlinModule)
        sdkImpl = StdSdkImpl(kodein)
        registerImpl(sdkImpl)
    }
    
    abstract fun initKodein(vararg modules: Kodein.Module): Kodein
    
    private fun registerImpl(sdkImpl: SdkImpl) {
        try {
            SdkImplRegistrar.registerAll(sdkImpl, datasource)
            DmTestdataRegistrar.registerAll(sdkImpl.testdata)
        } catch (e: Kodein.NotFoundException) {
            if (!isDebugMode()) throw e
            log.debugMode(e)
        }
    }
}