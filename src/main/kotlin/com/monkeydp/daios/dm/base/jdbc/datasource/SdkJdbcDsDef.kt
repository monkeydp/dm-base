package com.monkeydp.daios.dm.base.jdbc.datasource

import com.monkeydp.daios.dms.sdk.datasource.SdkDsDef
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinFieldComp
import com.monkeydp.tools.ext.kodein.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * @author iPotato
 * @date 2019/12/14
 */
@Target(FIELD)
@KodeinComponent(SdkJdbcDsDef.KodeinBuilderConfig::class)
annotation class SdkJdbcDsDef {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinFieldComp>() {
        override fun Kodein.Builder.config(comps: Collection<KodeinFieldComp>) {
            with(SdkDsDef.KodeinBuilderConfig) { config(comps) }
            bind<Iterable<JdbcDsDef>>() with singletonX { comps.map { it.value } }
        }
    }
}