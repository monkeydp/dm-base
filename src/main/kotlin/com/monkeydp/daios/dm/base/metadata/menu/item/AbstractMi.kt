package com.monkeydp.daios.dm.base.metadata.menu.item

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
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
        instr = def.instr
        name = def.name
        icon = def.icon
        hasSubmenu = def.menuDef != null
    }
    
    override fun toString() = name
}