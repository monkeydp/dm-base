package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDef
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDefs
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.dm.DmHelper

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi : AbstractConnApi() {
    
    private fun ConnProfile.findJdbcDsDef(): JdbcDsDef {
        val defs: JdbcDsDefs = DmHelper.findImpl()
        return defs.toSet().first { it.version == dsVersion }
    }
    
    protected fun ConnProfile.findDsDriverClassname() = findJdbcDsDef().driver.classname
}