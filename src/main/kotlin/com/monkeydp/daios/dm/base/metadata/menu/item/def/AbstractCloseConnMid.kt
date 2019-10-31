package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.CloseConnInstr
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractCloseConnMid(name: String = "Close Connection", icon: Icon<*>? = null) :
        AbstractMid(CloseConnInstr, name, icon)