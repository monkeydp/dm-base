package com.monkeydp.daios.dm.base.ui.node.def

import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.ui.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.ui.node.AbstractNd
import com.monkeydp.daios.dms.sdk.ui.node.NodeDef
import com.monkeydp.tools.ext.kotlin.singleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface GroupNd : NodeDef

abstract class AbstractGroupNd(name: String? = null) : GroupNd, AbstractNd() {
    override var target: Target<*> by Delegates.singleton(GlobalTarget.GROUP)
    override var name: String by Delegates.singleton(name ?: targetName)
    
    override fun defaultIcon(suffix: String) = super.defaultIcon(GlobalIcon.GROUP_SUFFIX)
}