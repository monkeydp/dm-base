package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDef
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi : AbstractConnApi() {
    override fun fullCp(cp: ConnProfile) =
            cp.copy(
                    dsDriverClassname = findDsDef(cp).driver.classname
            )
    
    abstract fun findDsDef(cp: ConnProfile): JdbcDsDef
}