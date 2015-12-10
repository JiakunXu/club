package com.wideka.club.framework.ibatis.type;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.wideka.club.framework.util.DateUtil;

/**
 * 
 * @author
 * 
 */
public class PreviousDateTypeHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter arg0) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		Date date = DateUtil.addDays((Date) parameter, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		setter.setString(df.format(date));
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}
