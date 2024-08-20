package com.example.mappers;

/**
 * @Description: 用户信息表Mapper
 * @author: Sly
 * @Date: 2024/08/20
 */
public interface UserInfoMapper<T, P> extends BaseMapper {

	//根据UserId查询
	 T selectByUserId();

	//根据Email查询
	 T selectByEmail();

	//根据QqOpenId查询
	 T selectByQqOpenId();

	//根据NickName查询
	 T selectByNickName();
}