package com.monkeydp.daios.dm.base.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.tools.ext.toPropListX

/**
 * @author iPotato
 * @date 2019/12/2
 */
abstract class AbstractDsDefs {
    fun toList() = toPropListX<DsDef>()
}