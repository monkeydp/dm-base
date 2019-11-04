package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemInfo
import com.monkeydp.daios.dms.sdk.metadata.menu.item.StdMi
import com.monkeydp.daios.dms.sdk.metadata.menu.item.def.MenuItemDef

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMid(
        private val instr: Instruction,
        private val name: String = "${instr.action.fullName} ${instr.target.fullName}",
        icon: Icon<*> = EMPTY_ICON
) : MenuItemDef {
    
    override var menuDef: MenuDef? = null
    
    override val info =
            MenuItemInfo(
                    instr = instr,
                    name = name,
                    icon = icon
            )
    
    override fun create() = StdMi(this)
    
    override fun toString() = "[DEF] $name"
}