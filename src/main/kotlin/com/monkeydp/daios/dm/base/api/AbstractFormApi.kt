package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.FormApi
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.received.form.ReceivedForm
import com.monkeydp.daios.dms.sdk.ui.form.Form
import com.monkeydp.daios.dms.sdk.ui.form.FormBuilder
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractFormApi : FormApi {
    
    private val receivedFormKClass: KClass<ReceivedForm> get() = dmKodeinRepo.findImpl()
    
    override fun loadFrom(instruction: Instruction): Form =
            FormBuilder.buildForm(receivedFormKClass)
}