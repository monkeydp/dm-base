package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
import com.monkeydp.daios.dms.sdk.share.kodein.putDmKodein
import com.monkeydp.tools.ext.logger.getLogger
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/4
 */
abstract class AbstractDmApp(private val dmKodein: Kodein) : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    init {
        putDmKodein(getDatasourceByClassname(), dmKodein)
    }
}