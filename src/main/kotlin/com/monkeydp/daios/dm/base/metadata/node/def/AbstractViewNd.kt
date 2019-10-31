package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfo

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractViewNd(name: String = "") : AbstractNd() {
    override val info =
            NodeInfo(GlobalTarget.VIEW, name, GlobalIcon.VIEW_ICON)
}