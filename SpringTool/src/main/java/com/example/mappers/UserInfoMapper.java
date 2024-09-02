package com.example.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 用户信息表Mapper
 * @author: Sly
 * @Date: 2024/09/02
 */
public interface UserInfoMapper<T, P> extends BaseMapper {

	//根据UserId查询
	 T selectByUserId(@Param("userId") String userId);

	//根据UserId更新
	 T updateByUserId(@Param("bean") T t, @Param("userId") String userId);

	//根据UserId删除
	 T deleteByUserId(@Param("userId") String userId);

	//根据QqOpenId查询
	 T selectByQqOpenId(@Param("qqOpenId") String qqOpenId);

	//根据QqOpenId更新
	 T updateByQqOpenId(@Param("bean") T t, @Param("qqOpenId") String qqOpenId);

	//根据QqOpenId删除
	 T deleteByQqOpenId(@Param("qqOpenId") String qqOpenId);

	//根据Email查询
	 T selectByEmail(@Param("email") String email);

	//根据Email更新
	 T updateByEmail(@Param("bean") T t, @Param("email") String email);

	//根据Email删除
	 T deleteByEmail(@Param("email") String email);

	//根据NickName查询
	 T selectByNickName(@Param("nickName") String nickName);

	//根据NickName更新
	 T updateByNickName(@Param("bean") T t, @Param("nickName") String nickName);

	//根据NickName删除
	 T deleteByNickName(@Param("nickName") String nickName);
}