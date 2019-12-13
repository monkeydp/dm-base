package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinComponentConfig

abstract class BaseKodeinComponentConfig : AbstractKodeinComponentConfig() {
    override val packageNames = listOf(PackageName.sdk, PackageName.dm)
}