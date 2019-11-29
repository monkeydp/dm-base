package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractDbNd : AbstractNd(
        target = GlobalTarget.DB,
        icon = GlobalIcon.DB_ICON
)