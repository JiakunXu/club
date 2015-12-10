package com.wideka.club.framework.ibatis.type;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * 
 * @author
 * 
 */
public class CharacterReplaceHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter getter) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object obj) throws SQLException {
		String parameter = (String) obj;
		parameter = parameter.replace("'", "");
		setter.setString(parameter);
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}
