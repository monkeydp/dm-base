package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/4
 */
interface MenuDef {
    val items: List<MenuItemDef>
    
    operator fun MenuItemDef.unaryPlus()
    operator fun Instruction.unaryPlus()
    fun create(): Menu
}

fun menuDef(init: MenuDef.() -> Unit): MenuDef = initInstance<StdMenuDef>(init)