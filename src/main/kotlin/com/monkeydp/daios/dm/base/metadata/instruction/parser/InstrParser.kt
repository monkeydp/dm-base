package com.monkeydp.daios.dm.base.metadata.instruction.parser

import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParser {
    val instr: Instruction
    fun parse(ctx: InstrParseCtx)
}