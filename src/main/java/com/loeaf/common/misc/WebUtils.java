/**
 * 
 */
package com.loeaf.common.misc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author gravity
 * @since 2020. 8. 14.
 *
 */
@Slf4j
public class WebUtils extends WebUtil {

	/**
	 * 어디서나 HttpServletRequest 구하기
	 * @see http://dveamer.github.io/backend/SpringRequestContextHolder.html
	 * @since
	 * 	20200817	init
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	/**
	 * 어디서나 HttpSession 구하기
	 * @see http://dveamer.github.io/backend/SpringRequestContextHolder.html
	 * @since
	 * 	20200817	init
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		if(null != request) {
			return request.getSession();
		}
		
		//
		log.error("<<.getSession - null request");
		return null;
	}
	
	
	/**
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}
