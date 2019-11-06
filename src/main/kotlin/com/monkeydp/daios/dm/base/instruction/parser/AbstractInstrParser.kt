package com.monkeydp.daios.dm.base.instruction.parser

import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.StdInstr
import com.monkeydp.daios.dms.sdk.instruction.ctx.InstrParseCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrParser<C : InstrParseCtx> : InstrParser<C> {
    override val instr =
            StdInstr(
                    InstrHelper.getActionByClassname(this),
                    InstrHelper.getTargetByClassname(this)
            )
}