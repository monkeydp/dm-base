package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.ManageGroupInstr
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDefImpl

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractManageGroupMid : AbstractMid(
        instr = ManageGroupInstr
)