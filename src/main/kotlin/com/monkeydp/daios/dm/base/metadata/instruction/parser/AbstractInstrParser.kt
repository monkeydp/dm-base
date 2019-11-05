package com.monkeydp.daios.dm.base.metadata.instruction.parser

import com.monkeydp.daios.dm.base.metadata.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.metadata.instruction.StdInstr

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrParser : InstrParser {
    override val instr =
            StdInstr(
                    InstrHelper.getActionByClassname(this),
                    InstrHelper.getTargetByClassname(this)
            )
}