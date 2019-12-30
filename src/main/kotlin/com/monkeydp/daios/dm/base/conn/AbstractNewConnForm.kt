package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.ui.JsType.NUMBER
import com.monkeydp.daios.dms.sdk.received.form.annot.ReceivedFormItem

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNewConnForm(
        @ReceivedFormItem(label = "Connection Name", required = true)
        override val connName: String,
        @ReceivedFormItem(required = true)
        override val host: String,
        @ReceivedFormItem(required = true, jsType = NUMBER)
        override val port: String,
        @ReceivedFormItem(label = "User Name", required = true)
        override val username: String,
        @ReceivedFormItem(required = true)
        override val password: String
) : NewConnForm