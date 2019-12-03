package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.ext.initInstance
import javax.persistence.Entity

/**
 * @author iPotato
 * @date 2019/12/3
 */
@Entity
class StdConnProfile : AbstractConnProfile() {
    override fun copy(
            userId: Long,
            datasource: Datasource,
            dsVersionId: String,
            userInput: UserInput
    ) = connProfile {
        this.userId = userId
        this.datasource = datasource
        this.dsVersionId = dsVersionId
        this.userInput = userInput
    }
}

fun connProfile(init: ConnProfile.() -> Unit) = initInstance<StdConnProfile>(init)