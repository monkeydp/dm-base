package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dm.base.ext.getDatasourceByClassname
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/9
 */
private var _dmKodeinMap = mutableMapOf<Datasource, Kodein>()
internal val dmKodeinMap get() = _dmKodeinMap.toMap()

fun putDmKodein(any: Any, dmKodein: Kodein) = putDmKodein(any.getDatasourceByClassname(), dmKodein)
private fun putDmKodein(datasource: Datasource, dmKodein: Kodein) {
    _dmKodeinMap[datasource] = dmKodein
}

fun getDmKodein(any: Any) = getDmKodein(any.getDatasourceByClassname())
private fun getDmKodein(datasource: Datasource) = _dmKodeinMap.getValue(datasource)