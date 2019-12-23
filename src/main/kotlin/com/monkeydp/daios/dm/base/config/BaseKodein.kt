package com.monkeydp.daios.dm.base.config

import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodein

/**
 * @author iPotato
 * @date 2019/12/23
 */
internal val kodein = DmKodein(BaseKodeinCompRepo)

private object BaseKodeinCompRepo : DmKodeinCompRepo() {
    override val annotReflections = reflections(
            packageNames = listOf(PackageName.sdk, PackageName.dmBase)
    )
    override val compReflections = reflections(
            packageNames = listOf(PackageName.dmBase)
    )
}