package com.monkeydp.daios.dm.base.instruction.parser

import com.monkeydp.daios.dms.sdk.instruction.InstrHelper

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrParser : InstrParser {
    override val instr = InstrHelper.getInstrByClassname(this)
}