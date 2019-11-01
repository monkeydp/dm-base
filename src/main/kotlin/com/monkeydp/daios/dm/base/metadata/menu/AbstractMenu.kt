package com.monkeydp.daios.dm.base.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/11/1
 */
abstract class AbstractMenu(items: List<MenuItem> = emptyList()) : Menu {
    override val items = items
}