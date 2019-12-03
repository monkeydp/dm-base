package com.monkeydp.daios.dm.base.jdbc.conn

import com.monkeydp.daios.dms.sdk.conn.JdbcConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.entity.Entity.Companion.INVALID_ID
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.ext.initInstance
import javax.persistence.Entity

/**
 * @author iPotato
 * @date 2019/12/3
 */
@Entity
class StdJdbcConnProfile : AbstractJdbcConnProfile() {
    override fun copy(userId: Long,
                      datasource: Datasource,
                      dsVersionId: String,
                      userInput: UserInput,
                      dsDriverClassname: String
    ) = jdbcConnProfile {
        this.userId = userId
        this.datasource = datasource
        this.dsVersionId = dsVersionId
        this.userInput = userInput
        this.dsDriverClassname = dsDriverClassname
    }
}

fun jdbcConnProfile(init: JdbcConnProfile.() -> Unit) = initInstance<StdJdbcConnProfile>(init)

