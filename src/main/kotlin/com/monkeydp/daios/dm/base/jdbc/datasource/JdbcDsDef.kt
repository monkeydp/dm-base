package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/12/3
 */
interface JdbcDsDef : DsDef {
    var driver: DsDriver
}

fun jdbcDsDef(init: JdbcDsDef.() -> Unit): JdbcDsDef = initInstance<StdJdbcDsDef>(init)