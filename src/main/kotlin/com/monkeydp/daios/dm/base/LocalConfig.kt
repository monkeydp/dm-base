package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/9
 */
interface LocalConfig {
    val apiMap: Map<KClass<*>, Any>
    val formKClassMap: Map<Instruction, KClass<*>>
    val parserMap: Map<Instruction, InstrParser>
}