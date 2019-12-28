package com.monkeydp.daios.dm.base.jdbc.api.node

import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcDbLoader {
    fun loadDbNodes(connection: Connection, def: NodeDef, sql: String): List<Node> {
        val nodes = mutableListOf<Node>()
        connection.prepareStatement(sql).use {
            it.executeQuery().use { resultSet ->
                while (resultSet.next())
                    nodes.add(def.create(resultSet.getString(1)))
            }
        }
        return nodes
    }
}