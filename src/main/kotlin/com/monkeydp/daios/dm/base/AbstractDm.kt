package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.metadata.menu.MenuStructInitializer
import com.monkeydp.daios.dm.base.metadata.node.NodeStructInitializer
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistrar
import com.monkeydp.daios.dms.sdk.dm.DmShareConfig
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistrar
import com.monkeydp.tools.ext.getLogger

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDm(shareConfig: DmShareConfig? = null) : Dm {
    
    companion object {
        private val log = getLogger()
    }
    
    abstract val config: LocalConfig
    
    init {
        if (shareConfig != null) updateConfig(shareConfig)
    }
    
    protected abstract fun updateConfig(config: DmShareConfig)
    
    /**
     * Should called by init{...} in subclass!
     */
    protected fun initialize() {
        log.info("Begin to register all dm static components!")
        DmImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    
        log.info("Begin to init metadata static struct!")
        NodeStructInitializer(config.nodeConfig)
        MenuStructInitializer(config)
        log.info("End to init metadata static struct!")
    }
}