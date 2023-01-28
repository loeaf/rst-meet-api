/**
 * 
 */
package com.loeaf.common.misc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 모든  interface의 부모
 * @author gravity
 * 
 */
public  interface Service<DOMAIN, IDTYPE>  {
	
	/**
	 * id(pk)로 1건 조회
	 * @param id
	 * @return
	 */
	DOMAIN findById(IDTYPE id);

	/**
	 * 전체 데이터 조회
	 * @return
	 */
	List<DOMAIN> findAll();
	
	/**
	 * 데이터 목록 조회  with paging and sort
	 * @param pageable
	 * @return
	 */
	Page<DOMAIN> findAll(Pageable pageable);
	
	/**
	 * 업무키로 1건 조회
	 * @param domain
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	DOMAIN findByBizKey(DOMAIN domain) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	/**
	 * 1건 삭제
	 * @param id
	 */
	void delete(IDTYPE id);
	
	/**
	 * n건 삭제
	 * @param ids
	 */
	void deleteAll(Iterable<IDTYPE> ids);
	
	/**
	 * 업무키로 데이터 삭제
	 * @param domain
	 * @return
	 */
	void deleteByBizKey(DOMAIN domain);

	/**
	 * 등록
	 * @param domain
	 */
	DOMAIN regist(DOMAIN domain);
	
	/**
	 * 수정
	 * @param id
	 * @param domain
	 */
	DOMAIN update(IDTYPE id, DOMAIN domain);
	
	/**
	 * n건 수정
	 * @param domains
	 */
	void updateAll(Iterable<DOMAIN> domains);
	
	/**
	 * n건 수정
	 * @param map
	 */
	void updateAll(Map<IDTYPE, DOMAIN> map);
	
	
	/**
	 * 업무키로 수정
	 * @param domain
	 * @return
	 */
	DOMAIN updateByBizKey(DOMAIN domain);


	/**
	 * Pageing
	 * @param startPage
	 * @param contentsSize
	 * @return
	 */
	Page<DOMAIN> findAllPgByStartPg(Integer startPage, Integer contentsSize);
	
	/**
	 * 아래는 mapper사용
	 */
	List<DOMAIN> registAll(List<DOMAIN> domain);
}
