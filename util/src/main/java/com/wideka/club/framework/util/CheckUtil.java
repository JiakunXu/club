package com.wideka.club.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author
 * 
 */
public final class CheckUtil {

	private CheckUtil() {

	}

	public static boolean regMail(String mail) {
		Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}

}
