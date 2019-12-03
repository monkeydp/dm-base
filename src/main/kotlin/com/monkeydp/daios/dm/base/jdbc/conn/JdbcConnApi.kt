package com.monkeydp.daios.dm.base.jdbc.conn

import com.monkeydp.daios.dms.sdk.api.ConnApi
import com.monkeydp.daios.dms.sdk.conn.JdbcConnProfile

/**
 * @author iPotato
 * @date 2019/12/3
 */
interface JdbcConnApi : ConnApi<JdbcConnProfile> {
    override fun fullCp(cp: JdbcConnProfile): JdbcConnProfile
    override fun getConn(cp: JdbcConnProfile): JdbcConn
}