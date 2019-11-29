package com.monkeydp.daios.dm.base.metadata.node.def

/**
 * @author iPotato
 * @date 2019/11/29
 */
abstract class AbstractNdStruct(def: NodeDef) : NodeDefStruct {
    override val struct = listOf(def)
}