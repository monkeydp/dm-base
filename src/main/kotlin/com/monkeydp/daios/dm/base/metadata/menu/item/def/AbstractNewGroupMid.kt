package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.NewGroupInstr
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDefImpl

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractNewGroupMid : AbstractMid(
        instr = NewGroupInstr
)