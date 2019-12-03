package com.monkeydp.daios.dm.base.metadata.node.def.abstr

import com.monkeydp.daios.dm.base.metadata.node.StdConnNode
import com.monkeydp.daios.dm.base.metadata.node.def.contract.ConnNd
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNd : ConnNd, AbstractNd() {
    override fun create(cp: ConnProfile) =
            StdConnNode(
                    cp = cp,
                    defId = id,
                    target = target,
                    icon = icon
            )
}