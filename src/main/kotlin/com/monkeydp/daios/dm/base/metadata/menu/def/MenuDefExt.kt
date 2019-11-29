package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dm.base.metadata.menu.item.def.StdMid

/**
 * @author iPotato
 * @date 2019/11/29
 */
fun menu(init: MenuDef.() -> Unit): MenuDef {
    val menuDef = StdMenuDef()
    menuDef.init()
    return menuDef
}

fun menuItem(init: MenuItemDef.() -> Unit): MenuItemDef {
    val mid = StdMid()
    mid.init()
    return mid
}