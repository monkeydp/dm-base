package com.monkeydp.daios.dm.base.metadata.menu.def

import com.monkeydp.daios.dm.base.metadata.menu.item.def.MenuItemDef
import com.monkeydp.daios.dm.base.metadata.menu.item.def.StdMid
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.StdMenu

/**
 * @author iPotato
 * @date 2019/11/1
 */
abstract class AbstractMenuDef : MenuDef {
    private val _items = mutableListOf<MenuItemDef>()
    override val items
        get() = _items.toList()
    
    override operator fun MenuItemDef.unaryPlus() {
        _items.add(this)
    }
    
    override fun Instruction.unaryPlus() {
        _items.add(StdMid(instr = this))
    }
    
    override fun create() = StdMenu(items.map { it.create() }.toList())
}