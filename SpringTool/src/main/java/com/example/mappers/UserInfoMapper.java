package com.example.mappers;

/**
 * @Description: 用户信息表Mapper
 * @author: Sly
 * @Date: 2024/08/15
 */
public interface UserInfoMapper extends BaseMapper {
	 T selectByUserId() {}
	 T selectByEmail() {}
	 T selectByQqOpenId() {}
	 T selectByNickName() {}
	 T selectByEmail() {}
}