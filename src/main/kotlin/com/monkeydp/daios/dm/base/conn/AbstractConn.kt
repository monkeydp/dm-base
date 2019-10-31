package com.monkeydp.daios.dm.base.conn

import com.monkeydp.daios.dms.sdk.conn.Conn

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractConn<C>(
        override val cpId: Long,
        override val rawConn: C
) : Conn<C>