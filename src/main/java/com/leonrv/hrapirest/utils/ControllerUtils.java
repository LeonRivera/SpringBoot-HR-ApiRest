package com.leonrv.hrapirest.utils;

import java.util.Map;

public class ControllerUtils {
    public static boolean validateAccess(Map<String, String> headers){
        String user = headers.get("user");
        String password = headers.get("password");

        if(user.equals("metaphorce") && password.equals("m3t4Ph0rc3")){
            return true;
        }else{
            return false;
        }
    }
}
