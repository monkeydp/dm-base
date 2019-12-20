package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/11/29
 */
internal class StdMid(
        instr: Instruction? = null,
        name: String? = null,
        icon: Icon<*>? = null
) : AbstractMid(instr, name, icon)