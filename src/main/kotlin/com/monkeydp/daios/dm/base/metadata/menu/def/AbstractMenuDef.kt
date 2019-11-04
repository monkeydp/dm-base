package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dms.sdk.metadata.menu.MenuDef
import com.monkeydp.daios.dms.sdk.metadata.menu.StdMenu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemDef

/**
 * @author iPotato
 * @date 2019/11/1
 */
abstract class AbstractMenuDef(
        override val items: List<MenuItemDef> = emptyList()
) : MenuDef {
    override fun create() = StdMenu(items.map { it.create() }.toList())
}