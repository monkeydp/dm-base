package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtx

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNodeApi : NodeApi {
    override fun loadSubNodes(ctx: NodeLoadCtx): List<Node> {
        val node = ctx.path.getLastNode()
        val subNodes = mutableListOf<Node>()
        node.children.forEach { subNodes.addAll(loadSubNodes(ctx, it)) }
        return subNodes
    }
    
    protected abstract fun loadSubNodes(ctx: NodeLoadCtx, node: Node): List<Node>
}