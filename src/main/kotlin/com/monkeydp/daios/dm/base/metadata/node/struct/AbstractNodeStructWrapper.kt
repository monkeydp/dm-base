package com.monkeydp.daios.dm.base.metadata.node.struct

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.node.struct.NodeStructWrapper

/**
 * @author iPotato
 * @date 2019/10/30
 */
abstract class AbstractNodeStructWrapper(override val structure: JsonNode) : NodeStructWrapper