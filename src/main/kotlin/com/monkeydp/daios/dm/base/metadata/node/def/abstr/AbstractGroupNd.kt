package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.GroupNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractGroupNd(name: String = "") : GroupNd, AbstractNd(
        target = GlobalTarget.GROUP,
        name = name,
        icon = GlobalIcon.GROUP_ICON
)