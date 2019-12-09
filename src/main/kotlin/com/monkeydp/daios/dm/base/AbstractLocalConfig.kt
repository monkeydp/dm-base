package com.monkeydp.daios.dm.base

import com.monkeydp.daios.dm.base.instruction.parser.InstrParser
import com.monkeydp.daios.dm.base.instruction.parser.InstrParserImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.main.SdkApi
import com.monkeydp.daios.dms.sdk.main.SdkApiContract
import com.monkeydp.daios.dms.sdk.main.SdkForm
import com.monkeydp.tools.ext.*
import kotlin.reflect.KClass

abstract class AbstractLocalConfig : LocalConfig {
    private val reflections = getReflections()
    
    override val apiMap: Map<KClass<*>, Any>
        get() {
            val apiSet = reflections.getAnnotSingletons(SdkApi::class)
            return apiSet.map { api ->
                val interfaces = api.javaClass.kotlin.getInterfaces()
                interfaces.matchOne { it.hasAnnotation<SdkApiContract>() } to api
            }.toMap()
        }
    
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