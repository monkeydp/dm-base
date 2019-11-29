package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.StdConnNode
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

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
                    target = target,
                    icon = icon,
                    childTargets = childTargets
            )
}