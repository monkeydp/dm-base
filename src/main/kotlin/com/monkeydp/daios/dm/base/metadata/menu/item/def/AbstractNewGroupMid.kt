package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.instruction.ManageGroupInstr
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractNewGroupMid(name: String = "New Group", icon: Icon<*>? = null) :
        AbstractMid(ManageGroupInstr, name, icon)