package com.monkeydp.daios.dm.base.metadata.node

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.node.AbstractNode
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode

/**
 * @author iPotato
 * @date 2019/10/25
 */
class StdConnNode(
        override val cp: ConnProfile,
        target: Target<*>,
        icon: Icon<*>,
        name: String = cp.form.connName,
        childTargets: List<Target<*>>
) : ConnNode, AbstractNode(target, name, icon, childTargets)
