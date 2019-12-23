package com.monkeydp.daios.dm.base.metadata.node.def

import com.monkeydp.daios.dm.base.metadata.menu.def.MenuDef
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.node.Node
import com.monkeydp.daios.dms.sdk.metadata.node.node
import com.monkeydp.tools.ext.kotlin.camelCase2List
import com.monkeydp.tools.ext.kotlin.lastOf
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import com.monkeydp.tools.ext.kotlin.valueOfOrNull
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface NodeDef {
    var id: Int
    var target: Target<*>
    var name: String
    var icon: Icon<*>
    var parent: NodeDef?
    val children: List<NodeDef>
    var menuDef: MenuDef?
    
    /**
     * Add a child by "+"
     */
    operator fun NodeDef.unaryPlus()
    
    /**
     * Create a node by current node def
     */
    fun create(name: String? = null): Node
}

abstract class AbstractNd : NodeDef {
    
    override var id by Delegates.notNullSingleton<Int>()
    
    protected val targetName = javaClass.simpleName.camelCase2List().lastOf(1)
    
    override var target: Target<*> by Delegates.notNullSingleton(defaultTarget())
    override var name: String by Delegates.notNullSingleton("")
    override var icon: Icon<*> by Delegates.notNullSingleton(defaultIcon())
    
    override var parent: NodeDef? = null
    private val _children = mutableListOf<NodeDef>()
    override val children
        get() = _children.toList()
    
    override var menuDef: MenuDef? = null
    
    override operator fun NodeDef.unaryPlus() {
        _children.add(this)
    }
    
    override fun create(name: String?) =
            node(
                    defId = id,
                    target = target,
                    name = name ?: this.name,
                    icon = icon
            )
    
    protected open fun defaultTarget() = GlobalTarget::class.valueOfOrNull(targetName)
    protected open fun defaultIcon(suffix: String = GlobalIcon.SUFFIX) =
            GlobalIcon::class.valueOfOrNull(GlobalIcon.appendSuffix(suffix)) ?: GlobalIcon.EMPTY_ICON
    
    override fun toString() = this.name
}