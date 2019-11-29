package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.ViewNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractViewNd : ViewNd, AbstractNd(
        target = GlobalTarget.VIEW,
        icon = GlobalIcon.VIEW_ICON
)