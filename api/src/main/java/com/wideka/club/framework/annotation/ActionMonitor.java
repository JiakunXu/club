package com.wideka.club.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于记录action操作日志.
 * 
 * example:
 * 
 * @ActionLog(actionName = "")
 * 
 * @author xujiakun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActionMonitor {

	/**
	 * 记录action操作名称.
	 */
	String actionName();

}
