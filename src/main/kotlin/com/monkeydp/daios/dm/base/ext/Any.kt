package com.monkeydp.daios.dm.base.ext

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.tools.ext.camelCaseFirst

/**
 * @author iPotato
 * @date 2019/12/9
 */
fun Any.getDsNameByClassname() = javaClass.simpleName.camelCaseFirst()

fun Any.getDatasourceByClassname() = Datasource.valueOf(getDsNameByClassname().toUpperCase())

internal fun Any.getDmKodein() = com.monkeydp.daios.dm.base.config.getDmKodein(this)