package com.monkeydp.daios.dm.base.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractInstr : Instruction {
    
    override val action = InstrHelper.getActionByClassname(this, 1)
    
    override val target = InstrHelper.getTargetByClassname(this, 0)
    
    override fun equals(other: Any?) =
            other is Instruction &&
            this.action == other.action &&
            this.target == other.target
}