package com.monkeydp.daios.dm.base.instruction.parser

import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.sdk.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParser {
    val instr: Instruction
    fun parse(ctx: InstrParsingCtx)
}