package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.main.SdkForm
import com.monkeydp.tools.ext.getAnnotKClasses
import com.monkeydp.tools.ext.getAnnotSingletonsX
import com.monkeydp.tools.ext.getReflections
import com.monkeydp.tools.ext.singletonX

abstract class AbstractLocalConfig : LocalConfig {
    private val reflections = getReflections()
    
    private val formAnnotKClass = SdkForm::class
    private val formKClasses by lazy { reflections.getAnnotKClasses(formAnnotKClass) }
    override val formKClassMap by lazy {
        formKClasses.map {
            val instrKClass = it.java.getAnnotation(formAnnotKClass.java).instrClass
            val instr = instrKClass.java.singletonX<Instruction>()
            instr to it
        }.toMap()
    }
    
    private val parsers by lazy { reflections.getAnnotSingletonsX<InstrParser>(InstrParserImpl::class) }
    override val parserMap by lazy { parsers.map { it.instr to it }.toMap() }
}