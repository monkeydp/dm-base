package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.node.NodeDef
import com.monkeydp.tools.exception.inner.InnerException

/**
 * @author iPotato
 * @date 2019/12/28
 */
class UnhandledNodeDefException(def: NodeDef) : InnerException("Unhandled node def: `${def.javaClass.name}`")