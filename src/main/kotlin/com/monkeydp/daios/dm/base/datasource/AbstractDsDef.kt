package com.monkeydp.daios.dm.base.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/2
 */
abstract class AbstractDsDef : DsDef {
    override var version: DsVersion<*> by Delegates.notNullSingleton()
}