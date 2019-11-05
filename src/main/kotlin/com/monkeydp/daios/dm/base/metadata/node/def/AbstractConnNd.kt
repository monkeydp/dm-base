package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.StdConnNode
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNd : AbstractNd(
        target = GlobalTarget.CONN,
        icon = GlobalIcon.CONN_ICON
) {
    fun create(cp: ConnProfile) =
            StdConnNode(
                    cp = cp,
                    target = info.target,
                    icon = info.icon
            )
}