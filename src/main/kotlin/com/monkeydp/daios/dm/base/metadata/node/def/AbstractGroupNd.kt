package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractGroupNd(name: String = "") : AbstractNd(
        target = GlobalTarget.GROUP,
        name = name,
        icon = GlobalIcon.GROUP_ICON
)