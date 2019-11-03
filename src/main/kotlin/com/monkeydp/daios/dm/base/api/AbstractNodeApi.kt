package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.daios.dms.sdk.metadata.node.ctx.NodeLoadCtx
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNodeApi : NodeApi {
    
    override fun loadSubNodes(ctx: NodeLoadCtx): List<Node> {
        val def = ctx.path.getLastNodeDef()
        val subNodes = mutableListOf<Node>()
        def.children.forEach { subNodes.addAll(loadNodes(ctx, it)) }
        return subNodes
    }
    
    protected abstract fun loadNodes(ctx: NodeLoadCtx, def: NodeDef): List<Node>
}