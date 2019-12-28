package com.monkeydp.daios.dm.base.api

import com.monkeydp.daios.dm.base.metadata.node.def.NodeDefStruct
import com.monkeydp.daios.dm.base.metadata.node.def.find
import com.monkeydp.daios.dm.base.metadata.node.def.sub.ConnNd
import com.monkeydp.daios.dms.sdk.api.NodeApi
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode
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
}