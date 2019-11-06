package com.monkeydp.daios.dm.base.instruction.parser

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.instruction.ctx.InstrParseCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
interface InstrParser<in C : InstrParseCtx> {
    val instr: Instruction
    fun parse(ctx: C)
}