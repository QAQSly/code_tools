package com.example.mappers;

/**
 * @Description: 用户信息表Mapper
 * @author: Sly
 * @Date: 2024/08/20
 */
public interface UserInfoMapper<T, P> extends BaseMapper {

	 T selectByUserId();

	 T selectByEmail();

	 T selectByQqOpenId();

	 T selectByNickName();

	 T selectByEmail();
}