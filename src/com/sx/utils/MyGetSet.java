package com.sx.utils;


public class MyGetSet {
    public static String getSetMethodName(String property) {

        return "set".concat(property.substring(0, 1).toUpperCase()).concat(property.substring(1, property.length()));
    }
    public static String getNameByGetMethod(String methodName){
        return methodName.substring(3,4).toLowerCase().concat(methodName.substring(4,methodName.length()));
    }

}
