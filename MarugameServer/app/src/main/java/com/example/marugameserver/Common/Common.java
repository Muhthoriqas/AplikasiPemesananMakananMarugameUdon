package com.example.marugameserver.Common;


import com.example.marugameserver.Model.Request;
import com.example.marugameserver.Model.User;

public class Common {
    public static User currentUser;

    public static Request currentRequest;

    public static  final  String UPDATE = "Update";
    public static  final  String DELETE = "Delete";

    public  static String convertCodeToStatus(String code){
        if(code.equals("0"))
            return "Tersedia";
        else if(code.equals("1"))
            return "Disiapkan";
        else
            return "Silahkan Ambil";
    }
}

