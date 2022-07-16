package com.leonrv.hrapirest.utils;

import java.util.regex.*;

public class EmployeeUtils {
    public static boolean validateRfc(String rfc){
        Pattern pattern = Pattern.compile("([A-Z]{4}[0-9]{6}[A-Z0-9]{3})");
		Matcher matcher = pattern.matcher(rfc);
		return matcher.matches();
    }
}
