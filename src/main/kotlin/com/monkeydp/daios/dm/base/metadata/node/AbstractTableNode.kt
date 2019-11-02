package com.monkeydp.daios.dm.base.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.AbstractNode

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractTableNode(
        def: TableNode? = null,
        name: String = ""
) : TableNode, AbstractNode(def, name) {
    override val target = GlobalTarget.TABLE
    override val icon = GlobalIcon.TABLE_ICON
}