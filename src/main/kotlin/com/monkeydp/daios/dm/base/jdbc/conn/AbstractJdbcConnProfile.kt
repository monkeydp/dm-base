package com.monkeydp.daios.dm.base.jdbc.conn

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dm.base.conn.AbstractConnProfile
import com.monkeydp.daios.dms.sdk.conn.JdbcConnProfile
import com.monkeydp.daios.dms.sdk.entity.Entity
import com.monkeydp.tools.ext.notNullSingleton
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/3
 */
@MappedSuperclass
abstract class AbstractJdbcConnProfile : JdbcConnProfile, AbstractConnProfile() {
    /**
     * @see DsDriver
     */
    override var dsDriverClassname: String by Delegates.notNullSingleton("")
        @Column(nullable = false)
        @JsonIgnore
        @ApiModelProperty(hidden = true)
        get
    
    @JsonIgnore
    override fun isValid() = super<JdbcConnProfile>.isValid() && dsDriverClassname.isNotBlank()
}