package com.leonrv.hrapirest.utils;

import java.util.regex.*;

import com.leonrv.hrapirest.models.*;

public class EmployeeUtils {
    public static boolean validateRfc(String rfc){
        Pattern pattern = Pattern.compile("([A-Z]{4}[0-9]{6}[A-Z0-9]{3})");
		Matcher matcher = pattern.matcher(rfc);
		return matcher.matches();
    }

    public static Contract getActiveContract(Employee employee){
        Contract contract = null;
        try {
            for(Contract c : employee.getContracts()){
                if(c.getIsActive()){
                    contract = c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return contract;
    }
}
