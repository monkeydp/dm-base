package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.OpenDbInstr

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractOpenDbMid : AbstractMid(
        instr = OpenDbInstr
)