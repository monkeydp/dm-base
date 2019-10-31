package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfo
import com.monkeydp.daios.dms.sdk.metadata.node.def.DbNd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDbNd(name: String = "") : DbNd, AbstractNd() {
    override val info =
            NodeInfo(GlobalTarget.DB, name, GlobalIcon.DB_ICON)
}