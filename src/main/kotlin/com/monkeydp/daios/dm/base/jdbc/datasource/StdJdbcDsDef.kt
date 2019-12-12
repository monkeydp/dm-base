package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/12/3
 */
class StdJdbcDsDef : AbstractJdbcDsDef()

fun jdbcDsDef(init: JdbcDsDef.() -> Unit) =
        initInstance<StdJdbcDsDef>(init)