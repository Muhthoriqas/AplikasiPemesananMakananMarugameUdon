package com.example.marugame.Common;

import com.example.marugame.Model.Request;
import com.example.marugame.Model.User;

public class Common {
    public static User currentUser;

    public static Request currentRequest;

    public static final String DELETE ="Delete";

    public  static String convertCodeToStatus(String code){
        if(code.equals("0"))
            return "Tersedia";
        else if(code.equals("1"))
            return "Disiapkan";
        else
            return "Silahkan Ambil Makanan";
    }
}
