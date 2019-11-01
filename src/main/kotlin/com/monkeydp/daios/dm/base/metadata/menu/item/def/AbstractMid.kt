package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.metadata.menu.item.info.MenuItemInfo
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMid(
        instr: Instruction,
        name: String = "",
        icon: Icon<*>? = null
) : MenuItemDef {
    override val info =
            MenuItemInfo(instr = instr, name = name, icon = icon ?: EMPTY_ICON)
    override val menu: Menu? = null
}