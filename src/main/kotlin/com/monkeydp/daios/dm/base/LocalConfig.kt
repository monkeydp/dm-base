package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/9
 */
interface LocalConfig {
    val componentsMap: Map<KClass<out Annotation>, Set<Any>>
    val components get() = componentsMap.values.toList().flatten()
    val formKClassMap: Map<Instruction, KClass<*>>
    val parserMap: Map<Instruction, InstrParser>
}