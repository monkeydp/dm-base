package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuPath

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractMenuApi : MenuApi {
    
    protected fun findNextDef(path: MenuPath, def: MenuDef) = recurFindNextDef(path.iterator(), def)
    
    private fun recurFindNextDef(iterator: MutableIterator<MenuItem>, def: MenuDef): MenuDef? {
        if (!iterator.hasNext()) return def
        val item = iterator.next()
        var nextDef = def.items.firstOrNull() { it.instr == item.instr }?.menuDef
        if (iterator.hasNext() && nextDef != null) nextDef = recurFindNextDef(iterator, nextDef)
        return nextDef
    }
}