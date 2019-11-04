package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.main.StdConnNode
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNd : ConnNd, AbstractNd(
        target = GlobalTarget.CONN,
        icon = GlobalIcon.CONN_ICON
) {
    override fun create(cp: ConnProfile) = StdConnNode(this, cp)
}