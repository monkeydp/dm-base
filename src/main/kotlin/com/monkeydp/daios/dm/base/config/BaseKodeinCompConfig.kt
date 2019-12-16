package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinCompConfig

abstract class BaseKodeinCompConfig : AbstractKodeinCompConfig() {
    override val packageNames = listOf(PackageName.sdk, PackageName.dm)
}