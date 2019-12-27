package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.tools.ext.kotlin.getActualTypeInSuperclassX
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.kotlin.toPropValues

/**
 * @author iPotato
 * @date 2019/12/27
 */
interface DsDefRepo<T : DsDef> {
    fun find(predicate: (T) -> Boolean): T
}

abstract class AbstractDsDefRepo<T : DsDef> : DsDefRepo<T> {
    private val dsDefClass: Class<out T> = getActualTypeInSuperclassX()
    override fun find(predicate: (T) -> Boolean): T = toPropValues(dsDefClass.kotlin).matchOne(predicate)
}