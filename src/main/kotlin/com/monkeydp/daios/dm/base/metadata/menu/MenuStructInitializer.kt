package com.monkeydp.daios.dm.base.metadata.menu

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dm.base.LocalConfig
import com.monkeydp.daios.dms.sdk.datasource.Datasource.MYSQL
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem
import com.monkeydp.tools.ext.ierror

/**
 * @author iPotato
 * @date 2019/11/1
 */
class MenuStructInitializer(config: LocalConfig) {
    
    companion object {
        @Volatile
        private var isInitialized = false
    }
    
    private val menuKey = "menu"
    private val itemsKey = "items"
    private val instrKey = "instr"
    private val actionKey = "action"
    private val targetKey = "target"
    
    private val config: LocalConfig
    
    init {
        this.config = config
        startParsing()
    }
    
    private fun startParsing() {
        if (isInitialized) return
        synchronized(isInitialized) {
            assignMenu2Node()
            isInitialized = true
        }
    }
    
    private fun assignMenu2Node() {
        val struct = config.menuConfig.struct["node"]
        struct.fields().forEach {
            val menuStruct: JsonNode? = it.value[menuKey]
            if (menuStruct != null)
                config.nodeConfig.defMap.getValue(it.key).menu = parseMenuStruct(menuStruct)
        }
    }
    
    private fun parseMenuStruct(menuStruct: JsonNode): Menu {
        val itemsStruct: JsonNode? = menuStruct[itemsKey]
        if (itemsStruct == null) ierror("Parsing menu error, a menu must have an items!")
        val items = parseItemsStruct(itemsStruct)
        return StdMenu(items)
    }
    
    private fun parseItemsStruct(itemsStruct: JsonNode) = itemsStruct.map { parseItemStruct(it) }.toList()
    
    private fun parseItemStruct(itemStruct: JsonNode): MenuItem {
        val instrStruct: JsonNode? = itemStruct[instrKey]
        val actionName: String
        val targetName: String
        if (instrStruct == null) {
            if (itemStruct.size() != 1) ierror("Menu item struct without prop named `instr` can only have one prop, which key is `action` and value is `target`!")
            val next = itemStruct.fields().next()
            actionName = next.key
            targetName = next.value.asText()
        } else {
            actionName = instrStruct[actionKey].asText()
            targetName = instrStruct[targetKey].asText()
        }
        val action = DmImplRegistry.getEnum<Action<*>>(actionName, MYSQL)
        val target = DmImplRegistry.getEnum<Target<*>>(targetName, MYSQL)
        val item = config.menuConfig.itemMap.getValue(Pair(action, target))
        
        val subMenuStruct = itemStruct[menuKey]
        if (subMenuStruct != null) {
            item.menu = parseMenuStruct(subMenuStruct)
        }
        return item
    }
}