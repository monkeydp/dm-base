package com.monkeydp.daios.dm.base.metadata.menu.item.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.item.StdMi
import com.monkeydp.tools.exception.inner.PropertyUninitializedException
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMid(
        instr: Instruction? = null,
        name: String? = null,
        icon: Icon<*>? = null
) : MenuItemDef {
    
    private val defaultInstr = instr ?: InstrHelper.getInstrOrNull(this)
    override var instr by Delegates.notNullSingleton(defaultInstr)
    
    private val _name: String? = name
    private val defaultName
        get() =
            try {
                _name ?: this.instr.toString()
            } catch (e: PropertyUninitializedException) {
                null
            }
    
    override var name: String by Delegates.notNullSingleton(::defaultName)
    
    private val defaultIcon = icon ?: EMPTY_ICON
    override var icon by Delegates.notNullSingleton(defaultIcon)
    
    override var menuDef: MenuDef? = null
    
    override fun create() = StdMi(
            instr = instr,
            name = name,
            icon = icon,
            hasSubmenu = menuDef != null
    )
    
    override fun toString() = "[DEF] $name"
}