package com.wideka.club.framework.ibatis.type;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * 
 * @author
 * 
 */
public class WildcardTypeHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter arg0) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object obj) throws SQLException {
		String parameter = (String) obj;

		if (StringUtils.isBlank(parameter)) {
			setter.setString("%");
		} else {
			parameter = parameter.replace("%", "\\%");
			parameter = parameter.replace("_", "\\_");

			setter.setString("%" + parameter + "%");
		}
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}
