package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDef
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi : AbstractConnApi() {
    abstract fun ConnProfile.findDsDef(): JdbcDsDef
    protected fun ConnProfile.findDsDriverClassname() = findDsDef().driver.classname
}