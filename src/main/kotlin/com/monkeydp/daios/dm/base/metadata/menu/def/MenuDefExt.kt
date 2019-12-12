package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dm.base.metadata.menu.item.def.StdMid
import com.monkeydp.tools.ext.kotlin.initInstance

/**
 * @author iPotato
 * @date 2019/11/29
 */
fun menu(init: MenuDef.() -> Unit) = initInstance<StdMenuDef>(init)

fun menuItem(init: MenuItemDef.() -> Unit) = initInstance<StdMid>(init)