package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dm.base.metadata.node.def.ConnNd
import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
class StdConnNode(
        def: ConnNd,
        cp: ConnProfile
) : AbstractConnNode(def, cp)
