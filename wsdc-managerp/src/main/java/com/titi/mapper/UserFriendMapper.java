package com.titi.mapper;

import com.titi.entity.CustInfoFormMap;
import com.titi.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 好友信息
 * @author lixiaoyu
 *
 */
public interface UserFriendMapper extends BaseMapper{

	/**
	 * 查询好友信息
	 * @param userId userId
	 * @return list
	 */
	List<CustInfoFormMap> findUserFriend(String userId);

	/**
	 * 删除好友
	 * @param userId userId
	 * @param friendId friendId
	 */
	void deleteUserFriend(@Param("userId") String userId, @Param("friendId") String friendId);

	/**
	 * 查找附近的人
	 * @param lat1 纬度1
	 * @param lon1 经度1
	 * @param lat2 纬度2
	 * @param lon2 经度2
	 * @return list
	 */
	List<CustInfoFormMap> findUserNearby(@Param("lat1") double lat1, @Param("lon1") double lon1,
                                         @Param("lat2") double lat2, @Param("lon2") double lon2);
}
