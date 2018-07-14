package com.yitongjia.util;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author bym @date 2018年7月4日
 *
 */
public class SessionUtil {

	///获取openid
	public static String getOpenid(HttpServletRequest request) {
		if (request.getParameter("openid") != null)
			return (String) request.getParameter("openid");
		else
			//return "ovN43wy5BKOY9xCe0WEvGD2An3lU";//"";//"otYxPt7dPvA7aFuj-PdXC1drvtHU";
			return "";
	}
	///获取status
		public static String getStatus(HttpServletRequest request) {
			if (request.getParameter("status") != null)
				return (String) request.getParameter("status");
			else
			
				return "";
		}
		//或缺nicname
		public static String getName(HttpServletRequest request) {
			if (request.getParameter("NickName") != null)
				return (String) request.getParameter("NickName");
			else
			
				return "";
		}
}
