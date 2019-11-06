package com.monkeydp.daios.dm.base.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.ConnNode

/**
 * @author iPotato
 * @date 2019/10/25
 */
class StdConnNode(
        override val cp: ConnProfile,
        override val target: Target<*>,
        override val icon: Icon<*>,
        override val name: String = cp.form.connName
) : ConnNode
