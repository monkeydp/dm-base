package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.GroupNd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractGroupNd(name: String = "Group") : GroupNd, AbstractNd() {
    override val info =
            NodeInfo(GlobalTarget.GROUP, name, GlobalIcon.GROUP_ICON)
}