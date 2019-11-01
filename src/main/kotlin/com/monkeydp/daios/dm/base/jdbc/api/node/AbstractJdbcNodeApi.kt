package com.monkeydp.daios.dm.base.jdbc.api.node

import com.monkeydp.daios.dm.base.api.AbstractNodeApi
import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtx
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractJdbcNodeApi : AbstractNodeApi() {
    
    override fun loadNodes(ctx: NodeLoadCtx): List<Node> {
        val nodeDef = ctx.path.getLastNodeDef()
        val nodes = mutableListOf<Node>()
        nodeDef.children.forEach { nodes.addAll(loadNodes(ctx, it)) }
        return nodes
    }
    
    protected abstract fun loadNodes(ctx: NodeLoadCtx, def: NodeDef): List<Node>
}