package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractViewNd : ViewNd, AbstractNd(
        target = GlobalTarget.VIEW,
        icon = GlobalIcon.VIEW_ICON
)