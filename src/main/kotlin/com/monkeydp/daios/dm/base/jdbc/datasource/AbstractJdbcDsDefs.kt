package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.daios.dm.base.datasource.AbstractDsDefs
import com.monkeydp.tools.ext.toPropValueSetX

/**
 * @author iPotato
 * @date 2019/12/2
 */
abstract class AbstractJdbcDsDefs : AbstractDsDefs() {
    override fun toSet() = toPropValueSetX<JdbcDsDef>()
}