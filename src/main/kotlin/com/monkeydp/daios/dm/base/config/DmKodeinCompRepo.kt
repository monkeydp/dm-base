package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.KodeinModuleRepo
import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinCompRepo

abstract class DmKodeinCompRepo : AbstractKodeinCompRepo() {
    override val modules = KodeinModuleRepo.modules()
    override val annotReflections = reflections(
            packageNames = listOf(PackageName.sdk, PackageName.dm)
    )
    override val compReflections = reflections(
            packageNames = listOf(PackageName.dm)
    )
}