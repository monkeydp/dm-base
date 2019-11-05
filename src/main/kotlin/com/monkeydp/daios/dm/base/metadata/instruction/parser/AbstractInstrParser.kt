package com.monkeydp.daios.dm.base.metadata.instruction.parser

import com.monkeydp.daios.dms.sdk.metadata.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.metadata.instruction.StdInstr
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtx

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