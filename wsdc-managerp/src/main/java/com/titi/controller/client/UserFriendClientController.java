package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.CustInfoFormMap;
import com.titi.entity.OutputResult;
import com.titi.mapper.UserFriendMapper;
import com.titi.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * 好友接口
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class UserFriendClientController extends BaseController {
	
	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserFriendClientController.class);

	@Inject
	UserFriendMapper userFriendMapper;

	
	/**
	 * 查询好友
	 *
	 */
	@RequestMapping(value = "getFriend.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询好友", methods = "getFriend.do")
	public @ResponseBody
	OutputResult selectFriend(String userId) {
		OutputResult output = new OutputResult();
		logger.info("查询好友信息：userId = " + userId);
		try {
			if (userId != null) {
				List<CustInfoFormMap> userFriends = userFriendMapper.findUserFriend(userId);
				output.setResult(Constants.RESULT_SUCCESS);
				output.setMsg("查询成功");
				output.setResultList(userFriends);
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("参数为空");
			}
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}

		return output;
	}

	/**
	 * 删除好友
	 *
	 */
	@RequestMapping(value = "delFriend.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "删除好友", methods = "delFriend.do")
	public @ResponseBody
	OutputResult delFriend(String userId, String friendId) {
		OutputResult output = new OutputResult();
		logger.info("删除好友：userId = " + userId);
		try {
			if (userId != null && friendId != null) {
				userFriendMapper.deleteUserFriend(userId, friendId);
				output.setResult(Constants.RESULT_SUCCESS);
				output.setMsg("删除成功");
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("参数为空");
			}
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}
		return output;
	}


	/**
	 * 用户每次进入app首先要获取位置坐标
	 * 查找附近的人
	 * 计算方法
	 * @param lat 纬度
	 * @param lon 经度
	 */
	@RequestMapping(value = "nearby.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "附近的人", methods = "nearby.do")
	public @ResponseBody
	OutputResult nearby(String lat, String lon) {
		OutputResult output = new OutputResult();
		try {
			if (lat != null && lon != null) {

				double lat0 = Double.parseDouble(lat);
				double lon0 = Double.parseDouble(lon);

				double lat1 = lat0 - 0.1;
				double lon1 = lon0 - 0.1;
				double lat2 = lat0 + 0.1;
				double lon2 = lon0 + 0.1;
				List<CustInfoFormMap> custInfoFormMapList = userFriendMapper.findUserNearby(lat1, lon1, lat2, lon2);
				if (custInfoFormMapList != null && !custInfoFormMapList.isEmpty()) {
					for (CustInfoFormMap custInfo : custInfoFormMapList) {
						custInfo.set("distance", getDistance(lat0, lon0, Double.parseDouble(custInfo.get("latitude").toString()),
								Double.parseDouble(custInfo.get("longitude").toString())));
					}
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("查询成功");
					output.setResultList(custInfoFormMapList);
				}else {
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("暂无附近的人");
				}
			}else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("参数为空");
			}

		}catch (NumberFormatException e) {
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("参数错误");
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}
		return output;
	}

	private static double getDistance(double lat1,double longt1 , double lat2,double longt2) {
		double PI = 3.14159265358979323; // 圆周率
		double R = 6371229; // 地球的半径
		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y)/1000;
//		DecimalFormat df = new DecimalFormat("#.00");
//		return Double.parseDouble(df.format(distance));
		return distance;
	}

	public static void main(String[] args){


		System.out.println(getDistance(40.59847032728547,40.69847032728747, 40.69847032728747,40.69847032728747));
	}





}