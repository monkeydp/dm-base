package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu

/**
 * @author iPotato
 * @date 2019/11/4
 */
interface MenuDef {
    val items: List<MenuItemDef>
    
    fun create(): Menu
}