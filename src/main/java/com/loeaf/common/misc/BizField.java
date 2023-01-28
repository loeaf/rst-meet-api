package com.loeaf.common.misc;

import java.lang.annotation.*;

/**
 * 필드에 사용되는 어노테이션
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BizField {

	/**
	 * 업무키 여부
	 * @return
	 */
	boolean bizKey() default true;
	
	/**
	 * 업무키 순서. 동적으로 쿼리 생성할 때 사용. 순서 중요. order순서가 jpa method name만들때 사용됨
	 * @return
	 */
	int order() default 0;
}
