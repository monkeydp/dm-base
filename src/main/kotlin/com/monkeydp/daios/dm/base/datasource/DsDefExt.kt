package com.monkeydp.daios.dm.base.datasource

import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.tools.ext.initInstance

/**
 * @author iPotato
 * @date 2019/12/2
 */
fun dsDef(init: DsDef.() -> Unit) = initInstance<StdDsDef>(init)