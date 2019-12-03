package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractConn<C : Any>(
        override val cpId: Long,
        override val rawConn: C
) : Conn<C> {
    override var id by Delegates.notNullSingleton<Long>()
    override fun toString() = "${javaClass.simpleName}(id=$id, cpId=$cpId, rawConn=$rawConn)"
}