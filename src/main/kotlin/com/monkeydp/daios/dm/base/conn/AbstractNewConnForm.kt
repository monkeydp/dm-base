package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.metadata.JsType.NUMBER
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.metadata.form.item.FormItemImpl

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNewConnForm(
        @FormItemImpl(label = "Connection Name", required = true)
        override val connName: String,
        @FormItemImpl(required = true)
        override val host: String,
        @FormItemImpl(required = true, jsType = NUMBER)
        override val port: String,
        @FormItemImpl(label = "User Name", required = true)
        override val username: String,
        @FormItemImpl(required = true)
        override val password: String
) : NewConnForm