package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDbsNd(name: String = "Databases") : AbstractGroupNd() {
    override val info = super.info.copy(name = name, icon = GlobalIcon.DB_GROUP_ICON)
}