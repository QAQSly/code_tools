package com.example.entity.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.utils.DateUtil;
import com.example.enums.DateTimePatternEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Description: 用户信息表
 * @author: Sly
 * @Date: 2024/07/26
 */
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {
	//用户ID
	@ToString.Include(name = "用户ID")
	private String userId;

	//用户昵称
	@ToString.Include(name = "用户昵称")
	private String nickName;

	//邮箱
	@ToString.Include(name = "邮箱")
	private String email;

	//QQ开放ID
	@ToString.Include(name = "QQ开放ID")
	@JsonIgnore
	private String qqOpenId;

	//QQ头像
	@ToString.Include(name = "QQ头像")
	private String qqAvatar;

	//密码
	@ToString.Include(name = "密码")
	private String password;

	//加入时间
	@ToString.Include(name = "加入时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	@ToString.Include(name="加入时间")
	public String joinTime() {
		return DateUtil.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
	}    //最后是登录一回

	@ToString.Include(name = "最后是登录一回")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	@ToString.Include(name="最后是登录一回")
	public String lastLoginTime() {
		return DateUtil.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
	}    //状态

	@ToString.Include(name = "状态")
	@JsonIgnore
	private Integer status;

	//已使用空间
	@ToString.Include(name = "已使用空间")
	private Long useSpace;

	//总空间
	@ToString.Include(name = "总空间")
	private Long totalSpace;

	//0: deleted, 1: normal
	@ToString.Include(name = "0: deleted, 1: normal")
	private Integer isDel;

	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo();
		userInfo.setJoinTime(new Date());
		userInfo.setLastLoginTime(new Date());
		System.out.println(userInfo);
	}

}