package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.OpenConnInstr
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractOpenConnMid(name: String = "Open Connection", icon: Icon<*>? = null) :
        AbstractMid(OpenConnInstr, name, icon)