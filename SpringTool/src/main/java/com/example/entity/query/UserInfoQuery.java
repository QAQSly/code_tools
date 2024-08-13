package com.example.entity.query;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Description: 用户信息表
 * @author: Sly
 * @Date: 2024/08/13
 */
@Getter
@Setter
@ToString
public class UserInfoQuery {
	//用户ID
	private String userId;

	//用户昵称
	private String nickName;

	//邮箱
	private String email;

	//QQ开放ID
	private String qqOpenId;

	//QQ头像
	private String qqAvatar;

	//密码
	private String password;

	//加入时间
	private Date joinTime;

	//最后是登录一回
	private Date lastLoginTime;

	//状态
	private Integer status;

	//已使用空间
	private Long useSpace;

	//总空间
	private Long totalSpace;

	//0: deleted, 1: normal
	private Integer isDel;

}