package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.GroupNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget.GROUP
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.Companion.GROUP_SUFFIX
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractGroupNd(name: String? = null) : GroupNd, AbstractNd() {
    override var target: Target<*> by Delegates.notNullSingleton(GROUP)
    override var name: String by Delegates.notNullSingleton(name ?: targetName)
    
    override fun defaultIcon(suffix: String) = super.defaultIcon(GROUP_SUFFIX)
}