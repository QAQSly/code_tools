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
	private String userId;

	private String nickName;

	private String email;

	private String qqOpenId;

	private String qqAvatar;

	private String password;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	private Date joinTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	private Date lastLoginTime;

	private Integer status;

	private Long useSpace;

	private Long totalSpace;

}