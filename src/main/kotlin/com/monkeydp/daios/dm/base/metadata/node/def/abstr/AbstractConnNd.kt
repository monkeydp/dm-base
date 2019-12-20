package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.def.contract.ConnNd
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.connNode

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNd : ConnNd, AbstractNd() {
    override fun create(cp: ConnProfile) =
            connNode(
                    cp = cp,
                    defId = id,
                    target = target,
                    icon = icon
            )
}