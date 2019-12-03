package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.daios.dm.base.datasource.AbstractDsDef
import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/2
 */
abstract class AbstractJdbcDsDef : JdbcDsDef, AbstractDsDef() {
    override var driver: DsDriver by Delegates.notNullSingleton()
}