package com.monkeydp.daios.dm.base.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/11/1
 */
class StdMenu(override var items: List<MenuItem> = emptyList()) : AbstractMenu(items)