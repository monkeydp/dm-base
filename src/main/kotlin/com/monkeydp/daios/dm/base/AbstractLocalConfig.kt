package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.main.SdkForm
import com.monkeydp.tools.ext.getAnnotKClasses
import com.monkeydp.tools.ext.getAnnotSingletonsX
import com.monkeydp.tools.ext.getReflections
import com.monkeydp.tools.ext.singletonInstanceX

abstract class AbstractLocalConfig {
    protected val reflections = getReflections()
    
    val formKClasses by lazy { reflections.getAnnotKClasses(SdkForm::class) }
    val formKClassMap by lazy {
        formKClasses.map {
            val instrKClass = it.java.getAnnotation(SdkForm::class.java).instrClass
            val instr = instrKClass.java.singletonInstanceX<Instruction>()
            instr to it
        }.toMap()
    }
    
    val parsers by lazy { reflections.getAnnotSingletonsX<InstrParser>(InstrParserImpl::class) }
    val parserMap by lazy { parsers.map { it.instr to it }.toMap() }
}