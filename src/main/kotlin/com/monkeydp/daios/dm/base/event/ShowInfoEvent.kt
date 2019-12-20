package com.monkeydp.daios.dm.base.event

import com.monkeydp.daios.dms.sdk.event.AbstractInstrEvent
import com.monkeydp.daios.dms.sdk.instruction.target.info.TargetInfo

/**
 * @author iPotato
 * @date 2019/11/7
 */
class ShowInfoEvent(
        source: Any,
        val info: TargetInfo
) : AbstractInstrEvent(source)