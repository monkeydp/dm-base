package com.monkeydp.daios.dm.base.conn

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import com.monkeydp.daios.dms.sdk.entity.Entity.Companion.INVALID_ID
import com.monkeydp.daios.dms.sdk.entity.User
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistry
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_USER_INPUT
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.ext.convertTo
import com.monkeydp.tools.ext.notNullSingleton
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.MappedSuperclass
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/6
 */
@ApiModel
@MappedSuperclass
abstract class AbstractConnProfile: ConnProfile, AbstractEntity() {
    
    override var userId: Long by Delegates.notNullSingleton(User.mockUser.id)
        @JsonIgnore
        @Column(nullable = false)
        get
    
    override var datasource: Datasource by Delegates.notNullSingleton()
        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(required = true, example = DATASOURCE)
        get
    
    override var dsVersionId: String by Delegates.notNullSingleton()
        @Column(nullable = false)
        @ApiModelProperty(value = "datasource version id", required = true, example = DS_VERSION_ID)
        get
    
    override val dsVersion: DsVersion<*>
        @JsonIgnore
        get() = SdkImplRegistry.findDsVersion(datasource, dsVersionId)
    
    override var userInput: UserInput by Delegates.notNullSingleton()
        @Column(nullable = false, length = 1024)
        @Convert(converter = UserInput.StringConverter::class)
        @ApiModelProperty(
                value = "parameters entered by the user",
                required = true,
                example = CP_USER_INPUT
        )
        get
    
    override val form: NewConnForm
        @JsonIgnore
        get() {
            val kClass = SdkImplRegistry.getImplKClass<NewConnForm>(datasource)
            return userInput.convertTo(kClass)
        }
    
    @JsonIgnore
    override fun isValid() = super<ConnProfile>.isValid()
}