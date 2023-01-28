/**
 * 
 */
package com.loeaf.common.misc;

import com.loeaf.common.domain.Domain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ControllerMaster {
	

	/**
	 * ResponseEntity 생성&리턴
	 *
	 * @param t
	 * @return
	 */
	protected <T> ResponseEntity<Map<String,Object>> res(T t){
		
		//
		Map<String,Object> map = new HashMap<>();
		map.put(Const.DATA, t);
		
		//
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * ResponseEntity 생성&리턴
	 * @param t Map의 자식
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected <T extends Map> ResponseEntity<Map<String,Object>> res(T t){
		
		//
		Map<String,Object> map = new HashMap<>();
		map.put(Const.DATA, t);
		
		//
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	/**
	 * ResponseEntity 생성&리턴
	 * @param vo
	 * @return
	 */
	protected <T extends Domain> ResponseEntity<Map<String,Object>> res(List<T> list){
		
		Map<String,Object> map = new HashMap<>();
		map.put(Const.DATA, list);
		
		//
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * ResponseEntity 생성&리턴
	 * @param vo
	 * @return
	 */
	protected <T extends Domain> ResponseEntity<Map<String,Object>> res(List<T> list, HttpStatus hs){
		
		Map<String,Object> map = new HashMap<>();
		map.put(Const.DATA, list);
		
		//
		return new ResponseEntity<>(map, hs);
	}
}
