package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.daios.dms.sdk.datasource.AbstractDsDef
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.daios.dms.sdk.datasource.DsVersion

/**
 * @author iPotato
 * @date 2019/12/3
 */
interface JdbcDsDef : DsDef {
    val driver: DsDriver
}

abstract class AbstractJdbcDsDef(
        version: DsVersion<*>,
        override val driver: DsDriver
) : JdbcDsDef, AbstractDsDef(version)