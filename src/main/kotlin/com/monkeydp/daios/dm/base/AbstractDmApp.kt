package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.config.putDmKodein
import com.monkeydp.daios.dms.sdk.dm.DmApp
import com.monkeydp.daios.dms.sdk.dm.DmConfig
import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
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
    
    init {
        dmKodein = initDmKodein(config.kotlinModule)
        putDmKodein(this, dmKodein)
    }
    
    abstract fun initDmKodein(vararg modules: Kodein.Module): Kodein
}