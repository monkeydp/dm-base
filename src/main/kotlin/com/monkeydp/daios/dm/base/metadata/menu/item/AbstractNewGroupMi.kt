package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dm.base.metadata.instruction.NewGroupInstr

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractNewGroupMi : AbstractMi() {
    override val instr = NewGroupInstr
}