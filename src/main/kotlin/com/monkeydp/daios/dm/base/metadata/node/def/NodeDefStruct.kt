package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.def.sub.ConnNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.metadata.node.Node

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface NodeDefStruct {
    val struct: List<NodeDef>
}

abstract class AbstractNdStruct(def: NodeDef) : NodeDefStruct {
    override val struct = listOf(def)
    private val defs = recurFlatten(struct)
    
    private fun recurFlatten(struct: List<NodeDef>): List<NodeDef> {
        val defs = mutableListOf<NodeDef>()
        struct.forEach {
            defs.add(it)
            defs.addAll(recurFlatten(it.children))
        }
        return defs.toList()
    }
    
    fun findConnNd() = findNd { it.target == GlobalTarget.CONN } as ConnNd
    
    protected fun findNd(filter: (NodeDef) -> Boolean) = defs.first(filter)
    
    fun findNd(node: Node) = defs.first { it.id == node.defId }
}