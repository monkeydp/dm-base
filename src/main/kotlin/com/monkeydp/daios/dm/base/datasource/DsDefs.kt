package com.monkeydp.daios.dm.base.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDef

/**
 * @author iPotato
 * @date 2019/12/9
 */
interface DsDefs<out DEF : DsDef> {
    fun toSet(): Set<DEF>
}