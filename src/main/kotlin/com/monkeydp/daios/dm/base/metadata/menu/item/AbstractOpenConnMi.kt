package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dm.base.metadata.instruction.OpenConnInstr

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractOpenConnMi : AbstractMi() {
    override val instr = OpenConnInstr
}