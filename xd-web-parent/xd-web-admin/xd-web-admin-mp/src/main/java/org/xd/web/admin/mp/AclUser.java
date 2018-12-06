package org.xd.web.admin.mp;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AclUser implements Serializable{

    private static final long serialVersionUID = 4193314191840207768L;
    /**
     *
     */

    @NotEmpty(message = "主键ID不能为空！")
    @ApiModelProperty(name = "主键ID",value = "id")
    private Integer id;

    @ApiModelProperty(name = "userName",value = "用户名称")
    private String userName;

    @ApiModelProperty(name = "mobile",value = "手机号码")
    private String mobile;

    @ApiModelProperty(name = "email",value = "邮箱地址")
    private String email;

    @ApiModelProperty(name = "idCard",value = "身份证号码")
    private String idCard;

    @ApiModelProperty(name = "realName",value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "interest",value = "兴趣")
    private String interest;

    @ApiModelProperty(name = "image",value = "头像URL")
    private String image;

    @ApiModelProperty(name = "birthday",value = "生日")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(name = "gender",value = "性别 1:男  0:女")
    private String gender;

    @ApiModelProperty(name = "introduction",value = "简介")
    private String introduction;
    @ApiModelProperty(name = "ethAccount",value = "eth地址")
    private String ethAccount;
    
    @JsonIgnore
    private String roleIds;

    /**
     * 人员类型 
		100000-管理员
		010000-普通用户
		001000-企业
		000100-专利持有人,权益人
		000010-合作伙伴
		000001-投资人
    */
    private String peopleType;

    @JsonIgnore
    private String userPwd;
    
    private Date createTime;

    private Date lastModifyTime;

    @JsonIgnore
    private Date lockTime;

    @JsonIgnore
    private String isLock;

    @JsonIgnore
    private Integer failCount;

    @ApiModelProperty(name = "signature",value = "签名")
    private String signature;

    @ApiModelProperty(name = "address",value = "地址")
    private String address;
    
    @ApiModelProperty(name = "contact",value = "联系方式")
    private String contact;
    
}