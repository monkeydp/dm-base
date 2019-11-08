package com.monkeydp.daios.dm.base.metadata.form

import com.monkeydp.daios.dms.sdk.metadata.JsType.NUMBER
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.form.item.FormItemInfo

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractCpForm(
        @FormItemInfo(label = "Connection Name", required = true)
        override val connName: String,
        @FormItemInfo(required = true)
        override val host: String,
        @FormItemInfo(required = true, jsType = NUMBER)
        override val port: String,
        @FormItemInfo(label = "User Name", required = true)
        override val username: String,
        @FormItemInfo(required = true)
        override val password: String
) : CpForm