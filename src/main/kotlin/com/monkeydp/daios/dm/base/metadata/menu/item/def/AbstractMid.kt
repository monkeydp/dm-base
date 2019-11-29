package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.item.StdMi

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMid(
        instr: Instruction? = null,
        name: String? = null,
        icon: Icon<*>? = null
) : MenuItemDef {
    
    override var instr = instr ?: InstrHelper.getInstrByClassname(this)
    override var name = name ?: "${this.instr.action.fullName} ${this.instr.target.fullName}"
    override var icon: Icon<*> = icon ?: EMPTY_ICON
    
    override var menuDef: MenuDef? = null
    
    override fun create() = StdMi(
            instr = instr,
            name = name,
            icon = icon,
            hasSubmenu = menuDef != null
    )
    
    override fun toString() = "[DEF] $name"
}