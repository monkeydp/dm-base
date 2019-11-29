package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.node.def.contract.ConnNd
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget.CONN
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author iPotato
 * @date 2019/11/29
 */
abstract class AbstractNdStruct(def: NodeDef) : NodeDefStruct {
    override val struct = listOf(def)
    private val defs = initDefs()
    
    private fun flatten() = recurGetDefs(struct)
    
    private fun recurGetDefs(struct: List<NodeDef>): List<NodeDef> {
        val defs = mutableListOf<NodeDef>()
        struct.forEach {
            defs.add(it)
            defs.addAll(recurGetDefs(it.children))
        }
        return defs.toList()
    }
    
    private fun initDefs(): List<NodeDef> {
        val defs = flatten()
        val count = AtomicInteger()
        defs.forEach { it.id = count.incrementAndGet() }
        return defs
    }
    
    fun findConnNd() = findNd { it.target == CONN } as ConnNd
    
    protected fun findNd(filter: (NodeDef) -> Boolean) = defs.first(filter)
    
    fun findNd(node: Node) = defs.first { it.id == node.defId }
}