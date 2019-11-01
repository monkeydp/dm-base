package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dm.base.metadata.instruction.ManageGroupInstr

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractManageGroupMi : AbstractMi() {
    override val instr = ManageGroupInstr
}