package com.monkeydp.daios.dm.base.metadata.node.def.contract

import com.monkeydp.daios.dm.base.metadata.node.def.AbstractNd
import com.monkeydp.daios.dm.base.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface GroupNd : NodeDef

abstract class AbstractGroupNd(name: String? = null) : GroupNd, AbstractNd() {
    override var target: Target<*> by Delegates.notNullSingleton(GlobalTarget.GROUP)
    override var name: String by Delegates.notNullSingleton(name ?: targetName)
    
    override fun defaultIcon(suffix: String) = super.defaultIcon(GlobalIcon.GROUP_SUFFIX)
}