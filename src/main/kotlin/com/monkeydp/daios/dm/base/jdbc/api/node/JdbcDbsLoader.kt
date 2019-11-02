package com.monkeydp.daios.dm.base.jdbc.api.node

import com.monkeydp.daios.dm.base.metadata.node.DbNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcDbsLoader {
    fun loadDbs(connection: Connection, def: DbNode, sql: String): List<DbNode> {
        val statement = connection.createStatement()
        val nodes = mutableListOf<DbNode>()
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