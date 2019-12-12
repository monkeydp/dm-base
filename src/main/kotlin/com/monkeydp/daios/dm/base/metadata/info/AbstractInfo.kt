package com.monkeydp.daios.dm.base.metadata.info

import com.monkeydp.daios.dms.sdk.metadata.info.Info
import com.monkeydp.tools.ext.kotlin.toJson

/**
 * @author iPotato
 * @date 2019/11/7
 */
abstract class AbstractInfo : Info {
    override fun toString() = if (data is String) data as String else data.toJson()
}