package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.TableNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractTableNd : TableNd, AbstractNd(
        target = GlobalTarget.TABLE,
        icon = GlobalIcon.TABLE_ICON
)