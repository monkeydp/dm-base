package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.ui.menu.Menu
import com.monkeydp.daios.dms.sdk.ui.menu.MenuDefStruct
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractMenuApi : MenuApi {
    
    private val mdStruct: MenuDefStruct get() = dmKodeinRepo.findImpl()
    
    override fun loadMenu(menuDefId: Int): Menu = mdStruct.find(menuDefId).create()
}