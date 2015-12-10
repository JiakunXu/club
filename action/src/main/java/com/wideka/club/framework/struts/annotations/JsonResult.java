package com.wideka.club.framework.struts.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于指示字段需要转换成JSON对象 一般include和exclude不会同时使用.
 * 
 * example:
 * 
 * @JsonResult(include = { "id", "name" }) private List<User> users = new
 *                     List<User>(); ... getter
 * 
 * @author tingjia.chentj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResult {

	/**
	 * 记录集字段的名字.
	 */
	String field();

	/**
	 * 总记录数字段名字.
	 * 
	 * @return
	 */
	String total() default "";

	/**
	 * 包含的属性.
	 */
	String[] include() default {

	};

	/**
	 * 排除的属性.
	 */
	String[] exclude() default {

	};

}
