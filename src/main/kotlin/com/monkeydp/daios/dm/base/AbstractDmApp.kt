package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodein
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.tools.ext.logger.getLogger

/**
 * @author iPotato
 * @date 2019/12/4
 */
abstract class AbstractDmApp(private val dmKodein: DmKodein) : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    init {
        dmKodeinRepo.putDmKodein(getDatasourceByClassname(), dmKodein)
    }
}