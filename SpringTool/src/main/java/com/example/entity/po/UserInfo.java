package com.example.entity.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *@Description: 用户信息表
 *@Date: 7/21/24, 11:05 AM
 */
@Getter
@Setter
public class UserInfo implements Serializable {
	private String userId;

	private String nickName;

	private String email;

	private String qqOpenId;

	private String qqAvatar;

	private String password;

	private Date joinTime;

	private Date lastLoginTime;

	private Integer status;

	private Long useSpace;

	private Long totalSpace;

}