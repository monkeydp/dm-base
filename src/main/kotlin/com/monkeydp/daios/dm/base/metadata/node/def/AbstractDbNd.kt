package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.StdDbNode
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractDbNd : DbNd, AbstractNd(
        target = GlobalTarget.DB,
        icon = GlobalIcon.DB_ICON
) {
    override fun create(name: String) = StdDbNode(this, name)
}