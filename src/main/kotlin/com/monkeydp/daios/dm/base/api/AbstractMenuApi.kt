package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dm.base.metadata.menu.MenuDef
import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuPath

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractMenuApi : MenuApi {
    
    protected fun findNextDef(path: MenuPath, def: MenuDef) = recurFindNextDef(path.iterator(), def)
    
    private tailrec fun recurFindNextDef(iterator: MutableIterator<MenuItem>, def: MenuDef): MenuDef {
        if (!iterator.hasNext()) return def
        val item = iterator.next()
        val nextDef = def.items.first() { it.id == item.defId }.menuDef!!
        return recurFindNextDef(iterator, nextDef)
    }
}