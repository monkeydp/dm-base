package com.monkeydp.daios.dm.base.jdbc.conn

import com.monkeydp.daios.dm.base.conn.AbstractConn
import com.monkeydp.daios.dms.sdk.conn.Conn
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/12/3
 */
interface JdbcConn : Conn<Connection>

abstract class AbstractJdbcConn(
        cpId: Long,
        rawConn: Connection
) : JdbcConn, AbstractConn<Connection>(cpId, rawConn) {
    
    override fun isValid(timeout: Int) = rawConn.isValid(timeout)
    
    override fun close(): Unit = rawConn.close()
    
    override fun isClosed() = rawConn.isClosed
}