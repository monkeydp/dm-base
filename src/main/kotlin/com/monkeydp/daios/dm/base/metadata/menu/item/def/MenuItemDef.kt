package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    var instr: Instruction
    var name: String
    var icon: Icon<*>
    var menuDef: MenuDef?
    
    fun create(): MenuItem
}

fun menuItemDef(init: MenuItemDef.() -> Unit): MenuItemDef = initInstance<StdMid>(init)