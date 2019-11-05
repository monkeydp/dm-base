package com.monkeydp.daios.dm.base.metadata.instruction

import com.monkeydp.daios.dms.sdk.config.GlobalConfig.globalActionClass
import com.monkeydp.daios.dms.sdk.config.GlobalConfig.globalTargetClass
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf
import com.monkeydp.tools.ext.valueOf

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractInstr : Instruction {
    
    override val action: Action<*> by lazy {
        val simpleName = this.javaClass.simpleName
        val strs = simpleName.camelCase2List()
        globalActionClass.valueOf(strs.lastOf(1).toUpperCase())
    }
    
    override val target: Target<*> by lazy {
        val simpleName = this.javaClass.simpleName
        val strs = simpleName.camelCase2List()
        globalTargetClass.valueOf(strs.lastOf(0).toUpperCase())
    }
    
    override fun equals(other: Any?) =
            other is Instruction &&
            this.action == other.action &&
            this.target == other.target
}