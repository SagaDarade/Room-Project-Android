package com.example.projectroom.Interfare;

public class SubStrConvert {

    public String convert(String s) {
        String[] a = s.split("\\[");
        return "["+a[1];
    }

    public String getSubString(String s){
        int i = s.indexOf("[");
        s = s.substring(i,s.length());
        return s;
    }
}
