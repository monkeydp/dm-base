package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.conn.JdbcConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDef
import com.monkeydp.daios.dms.sdk.conn.JdbcConnProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi : JdbcConnApi, AbstractConnApi<JdbcConnProfile>() {
    override fun fullCp(cp: JdbcConnProfile) =
            cp.copy(
                    dsDriverClassname = findDsDef(cp).driver.classname
            )
    
    abstract fun findDsDef(cp: JdbcConnProfile): JdbcDsDef
}