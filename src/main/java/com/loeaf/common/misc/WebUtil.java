package com.loeaf.common.misc;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	/**
	 * 접속자 아이피 구하기
	 * @param request
	 * @return
	 */
	public static String getConectIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		
		if(Utils.isEmpty(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(Utils.isEmpty(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(Utils.isEmpty(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if(Utils.isEmpty(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(Utils.isEmpty(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if(Utils.isEmpty(ip)) {
			ip = request.getRemoteAddr();
		}
		
		//
		return ip;
	}	
}
