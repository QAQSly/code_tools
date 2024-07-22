package com.example.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 用户信息表
 * @author: Sly
 * @Date: 2024/07/22
 */
@Getter
@Setter
public class UserInfo implements Serializable {
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	private Date joinTime;

	//最后是登录一回
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	private Date lastLoginTime;

	//状态
	private Integer status;

	//已使用空间
	private Long useSpace;

	//总空间
	private Long totalSpace;

}