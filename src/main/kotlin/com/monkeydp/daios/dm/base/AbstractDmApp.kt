package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.config.kodein.dmsShareKodeinModule
import com.monkeydp.daios.dms.sdk.config.kodein.putDmShareKodein
import com.monkeydp.tools.ext.logger.getLogger
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/4
 */
abstract class AbstractDmApp : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    private val dmShareKodein: Kodein
    
    init {
        dmShareKodein = initDmShareKodein(dmsShareKodeinModule)
        putDmShareKodein(this, dmShareKodein)
    }
    
    protected abstract fun initDmShareKodein(vararg modules: Kodein.Module): Kodein
}