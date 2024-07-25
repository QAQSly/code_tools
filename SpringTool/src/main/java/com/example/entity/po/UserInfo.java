package com.example.entity.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Description: 用户信息表
 * @author: Sly
 * @Date: 2024/07/25
 */
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {
	//用户ID
	private String userId;

	//用户昵称
	private String nickName;

	//邮箱
	private String email;

	//QQ开放ID
	@JsonIgnore
	private String qqOpenId;

	//QQ头像
	private String qqAvatar;

	//密码
	private String password;

	//加入时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	//最后是登录一回
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	//状态
	@JsonIgnore
	private Integer status;

	//已使用空间
	private Long useSpace;

	//总空间
	private Long totalSpace;

	//0: deleted, 1: normal
	private Integer isDel;

}