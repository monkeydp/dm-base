package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl
import com.monkeydp.daios.dms.sdk.instruction.InstrParser
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrApi : InstrApi {
    override fun parse(ctx: InstrParsingCtx) =
            dmKodeinRepo.findImpl<InstrParser>(tag = ctx.instr).parse(ctx)
}