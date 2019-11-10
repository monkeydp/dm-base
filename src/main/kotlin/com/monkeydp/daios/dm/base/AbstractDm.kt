package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.metadata.menu.MenuStructInitializer
import com.monkeydp.daios.dm.base.metadata.node.NodeStructInitializer
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistrar
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmOpenConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
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
    
    abstract val config: LocalConfig
    
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
    
        log.info("Begin to init metadata static struct!")
        NodeStructInitializer(config.nodeConfig)
        MenuStructInitializer(datasource, config)
        log.info("End to init metadata static struct!")
    }
}