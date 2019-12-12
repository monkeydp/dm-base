package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.helper.IdHelper.INVALID_ID
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractConn<C : Any>(
        override val cpId: Long,
        override val rawConn: C
) : Conn<C> {
    override var id by Delegates.notNullSingleton<Long>(INVALID_ID)
    override fun toString() = "${javaClass.simpleName}(id=$id, cpId=$cpId, rawConn=$rawConn)"
}