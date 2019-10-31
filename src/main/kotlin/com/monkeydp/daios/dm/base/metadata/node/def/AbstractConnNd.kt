package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.ConnNd

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractConnNd(name: String = "") : ConnNd, AbstractNd() {
    override val info =
            NodeInfo(GlobalTarget.CONN, name, GlobalIcon.CONN_ICON)
}