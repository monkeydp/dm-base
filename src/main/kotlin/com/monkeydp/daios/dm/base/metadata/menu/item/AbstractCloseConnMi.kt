package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dm.base.metadata.instruction.CloseConnInstr

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractCloseConnMi : AbstractMi() {
    override val instr = CloseConnInstr
}