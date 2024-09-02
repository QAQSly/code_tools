package com.example.entity.query;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Description: 用户信息表查询对象
 * @author: Sly
 * @Date: 2024/09/02
 */
@Getter
@Setter
@ToString
public class UserInfoQuery {
	//用户ID
	private String userId;

	private String userIdFuzzy;

	//用户昵称
	private String nickName;

	private String nickNameFuzzy;

	//邮箱
	private String email;

	private String emailFuzzy;

	//QQ开放ID
	private String qqOpenId;

	private String qqOpenIdFuzzy;

	//QQ头像
	private String qqAvatar;

	private String qqAvatarFuzzy;

	//密码
	private String password;

	private String passwordFuzzy;

	//加入时间
	private Date joinTime;

	private Date joinTimeStart;

	private Date joinTimeEnd;

	//最后是登录一回
	private Date lastLoginTime;

	private Date lastLoginTimeStart;

	private Date lastLoginTimeEnd;

	//状态
	private Integer status;

	//已使用空间
	private Long useSpace;

	//总空间
	private Long totalSpace;

	//0: deleted, 1: normal
	private Integer isDel;

}