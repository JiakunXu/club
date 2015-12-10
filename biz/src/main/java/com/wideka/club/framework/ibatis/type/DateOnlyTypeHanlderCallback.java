package com.wideka.club.framework.ibatis.type;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * 
 * @author
 * 
 */
public class DateOnlyTypeHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter arg0) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		setter.setString(df.format(parameter));
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}
