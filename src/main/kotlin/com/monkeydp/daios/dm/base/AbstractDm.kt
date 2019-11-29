package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmOpenConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistrar
import com.monkeydp.tools.ext.getLogger

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDm(openConfig: DmOpenConfig) : Dm {
    
    override val impl = StdSdkImpl(this)
    
    val eventPublisher = openConfig.eventPublisher
    
    companion object {
        private val log = getLogger()
    }
    
    init {
        if (!openConfig.isMock) updateOpenConfig(openConfig)
    }
    
    protected abstract fun updateOpenConfig(config: DmOpenConfig)
    
    /**
     * Should called by init{...} in subclass!
     */
    protected fun initialize() {
        log.info("Begin to register all dm static components!")
        SdkImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    }
}