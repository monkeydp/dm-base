package com.monkeydp.daios.dm.base.metadata.node.main

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.node.def.ConnNd
import com.monkeydp.daios.dms.sdk.metadata.node.main.AbstractNode
import com.monkeydp.daios.dms.sdk.metadata.node.main.ConnNode

/**
 * @author iPotato
 * @date 2019/11/3
 */
abstract class AbstractConnNode(
        def: ConnNd,
        override val cp: ConnProfile
) : ConnNode, AbstractNode(def, cp.form.connName)