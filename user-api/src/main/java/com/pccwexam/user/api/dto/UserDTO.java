package com.pccwexam.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@ApiModel("User")
@Data
public class UserDTO {

    @ApiModelProperty("User id")
    private Long id;
    @ApiModelProperty(value = "User email as unique username", required = true)
    @Email(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "Invalid email")
    private String email;
    @ApiModelProperty(value = "User password", required = true)
    @Pattern(regexp = "^\\d{6}$", message = "Password must be six digits")
    private String password;
    @ApiModelProperty("User nickname")
    private String nickname;
    @ApiModelProperty("1:activated 0:deleted")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @JsonIgnore
    private boolean exist;

}
