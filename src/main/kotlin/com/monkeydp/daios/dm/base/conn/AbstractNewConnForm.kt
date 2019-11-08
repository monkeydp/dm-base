package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.metadata.JsType.NUMBER
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.metadata.form.item.SdkFormItem

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNewConnForm(
        @SdkFormItem(label = "Connection Name", required = true)
        override val connName: String,
        @SdkFormItem(required = true)
        override val host: String,
        @SdkFormItem(required = true, jsType = NUMBER)
        override val port: String,
        @SdkFormItem(label = "User Name", required = true)
        override val username: String,
        @SdkFormItem(required = true)
        override val password: String
) : NewConnForm