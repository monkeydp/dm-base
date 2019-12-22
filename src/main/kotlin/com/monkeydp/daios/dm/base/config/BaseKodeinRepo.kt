package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.abstr.AbstractKodeinRepo

abstract class BaseKodeinRepo : AbstractKodeinRepo() {
    override val annotReflections = reflections(
            packageNames = listOf(PackageName.sdk, PackageName.dm)
    )
    override val compReflections = reflections(
            packageNames = listOf(PackageName.dm)
    )
}