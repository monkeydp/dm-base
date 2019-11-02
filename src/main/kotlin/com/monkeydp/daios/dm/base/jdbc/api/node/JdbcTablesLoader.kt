package com.monkeydp.daios.dm.base.jdbc.api.node

import com.monkeydp.daios.dm.base.metadata.node.TableNode
import java.sql.Connection

/**
 * @author iPotato
 * @date 2019/10/29
 */
object JdbcTablesLoader {
    fun loadTables(connection: Connection, def: TableNode, sql: String): List<TableNode> {
        val statement = connection.createStatement()
        val nodes = mutableListOf<TableNode>()
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