package com.monkeydp.daios.dm.base.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDriver
import com.monkeydp.daios.dms.sdk.datasource.DsVersion

/**
 * @author iPotato
 * @date 2019/12/2
 */
class StdDsDef : AbstractDsDef() {
    override lateinit var version: DsVersion<*>
    override var driver: DsDriver = DsDriver.empty()
}