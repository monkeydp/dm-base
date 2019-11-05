package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemStatus
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemStatus.ENABLED

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMi : MenuItem {
    
    override val instr: Instruction
    override val name: String
    override val icon: Icon<*>
    override val hasSubmenu: Boolean
    override var status: MenuItemStatus = ENABLED
    
    constructor(def: MenuItemDef) {
        val info = def.info
        instr = info.instr
        name = info.name
        icon = info.icon
        hasSubmenu = def.menuDef != null
    }
    
    override fun toString() = name
}