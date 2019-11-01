package com.monkeydp.daios.dm.base.metadata.menu.item

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/10/31
 */
abstract class AbstractMi : MenuItem {
    override val name by lazy { "${instr.action.fullName} ${instr.target.fullName}" }
    override val icon = EMPTY_ICON
    @JsonIgnore
    override var menu: Menu? = null
    override val hasSubmenu by lazy { menu != null }
    
    override fun toString() = name
}