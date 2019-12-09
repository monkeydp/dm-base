package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDefs
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi(private val kodein: Kodein) : AbstractConnApi() {
    private val defs: JdbcDsDefs by kodein.instance()
    private fun ConnProfile.findJdbcDsDef() = defs.toSet().first { it.version == dsVersion }
    protected fun ConnProfile.findDsDriverClassname() = findJdbcDsDef().driver.classname
}