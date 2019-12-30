package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.daios.dms.sdk.instruction.InstrParser
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrApi : InstrApi {
    override fun parse(ctx: InstrParsingCtx) {
        val parser: InstrParser = dmKodeinRepo.findImpl(tag = ctx.instr)
        parser.parse(ctx)
    }
}