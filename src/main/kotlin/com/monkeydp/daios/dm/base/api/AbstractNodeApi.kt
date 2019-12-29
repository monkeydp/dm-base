package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dm.base.metadata.node.def.ConnNd
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.*
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNodeApi : NodeApi {
    
    protected val ndStruct: NodeDefStruct get() = dmKodeinRepo.findImpl()
    
    override fun loadConnNodes(cps: Iterable<ConnProfile>): List<ConnNode> =
            ndStruct.find<ConnNd>().run { cps.map { create(it) } }
    
    override fun loadSubNodes(path: NodePath): List<Node> =
            path.getLastNodeDef().children.map { loadNodes(path, it) }.flatten()
    
    protected abstract fun loadNodes(path: NodePath, def: NodeDef): List<Node>
}