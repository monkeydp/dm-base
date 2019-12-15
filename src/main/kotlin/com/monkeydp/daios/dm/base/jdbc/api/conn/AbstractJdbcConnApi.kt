package com.monkeydp.daios.dm.base.jdbc.api.conn

import com.monkeydp.daios.dm.base.api.AbstractConnApi
import com.monkeydp.daios.dm.base.jdbc.datasource.JdbcDsDef
import com.monkeydp.daios.dms.sdk.annot.SdkDsDef
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.share.kodein.DmShareKodeinHelper

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractJdbcConnApi : AbstractConnApi() {
    
    private fun ConnProfile.findJdbcDsDef(): JdbcDsDef {
        val defs: Set<JdbcDsDef> = DmShareKodeinHelper.findImpl(tag = SdkDsDef::class)
        return defs.first { it.version == dsVersion }
    }
    
    protected fun ConnProfile.findDsDriverClassname() = findJdbcDsDef().driver.classname
}