package com.monkeydp.daios.dm.base.jdbc.api.node

import com.monkeydp.daios.dm.base.metadata.node.def.DbNd
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcDbsLoader {
    fun loadDbs(connection: Connection, def: DbNd, sql: String): List<Node> {
        val statement = connection.createStatement()
        val nodes = mutableListOf<Node>()
        statement.use {
            val resultSet = it.executeQuery(sql)
            resultSet.use {
                while (resultSet.next())
                    nodes.add(def.create(resultSet.getString(1)))
            }
        }
        return nodes
    }
}